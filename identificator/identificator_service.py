import pymongo
from bson.objectid import ObjectId
import os

from feature_extractor_client import FeatureExtractorClient
from feature_extractor_pb2 import FeatureExtractionRequest
from identificator_pb2 import IdentificationResponse, VerificationResponse, Candidate, Criminal, Address
from identificator_pb2_grpc import IdentificatorServicer
from matcher_client import MatcherClient
from matcher_pb2 import MatchFingerprintsRequest, MatchFingerprintTemplate


class IdentificatorService(IdentificatorServicer):

    def __init__(self, *args, **kwargs):
        self.matcher_client = MatcherClient()
        self.feature_extractor_client = FeatureExtractorClient()
        self.mongo_client = pymongo.MongoClient(os.getenv('DP_MONGO_HOST', 'localhost'),
                                                int(os.getenv('DP_MONGO_PORT', '27017')))

    def _get_criminal_from_db(self, criminal_id: str):
        return self.mongo_client.dp_criminal.criminal.find_one(ObjectId(criminal_id))

    @staticmethod
    def _create_candidate_from_criminal(criminal, match_score):
        return Candidate(
            criminal=Criminal(
                id=str(criminal["_id"]),
                firstName=criminal["firstName"],
                lastName=criminal["lastName"],
                phoneNumber=criminal["phoneNumber"],
                email=criminal["email"],
                address=Address(
                    country=criminal["address"]["country"],
                    city=criminal["address"]["city"],
                    street=criminal["address"]["street"],
                    streetNumber=criminal["address"]["streetNumber"],
                    postCode=criminal["address"]["postCode"]
                )
            ),
            matchScore=match_score
        )

    def Identify(self, request, context):
        modality_type = request.modalityType
        sample_data = request.data
        match_score_threshold = request.identificationParameters.matchScoreThreshold
        candidate_count = request.identificationParameters.candidateCount

        # extract features from sample data
        if modality_type == "FINGERPRINT":
            sample_fingerprint_extraction_response = \
                self.feature_extractor_client.extract_fingerprint(FeatureExtractionRequest(data=sample_data))

        hitlist = []

        # query criminal biom. data (modalities) from db
        for modality in self.mongo_client.dp_criminal.modality.find():
            if modality["type"] == "FINGERPRINT":
                match_request = MatchFingerprintsRequest(
                    sampleTemplate=MatchFingerprintTemplate(
                        keypointsSize=sample_fingerprint_extraction_response.keypointsSize,
                        encodedDescriptor=sample_fingerprint_extraction_response.encodedDescriptor
                    ),
                    realTemplate=MatchFingerprintTemplate(
                        keypointsSize=modality["keypointsSize"],
                        encodedDescriptor=modality["encodedDescriptor"]
                    )
                )

                match_response = self.matcher_client.match_fingerprints(match_request)
                match_score = match_response.matchScore

                if match_score_threshold <= match_score <= 100.0:
                    criminal = self._get_criminal_from_db(criminal_id=modality["criminalId"])

                    if criminal is not None:
                        candidate = self._create_candidate_from_criminal(criminal=criminal, match_score=match_score)

                        hitlist.append(candidate)

        hitlist.sort(reverse=True, key=lambda c: c.matchScore)
        hitlist = hitlist[0:candidate_count]

        return IdentificationResponse(hitlist=hitlist)

    def Verify(self, request, context):
        modality_type = request.modalityType
        sample_data = request.data
        criminal_id = request.criminalId
        match_score_threshold = request.verificationParameters.matchScoreThreshold
        verified = False

        # extract features from sample data
        if modality_type == "FINGERPRINT":
            sample_fingerprint_extraction_response = \
                self.feature_extractor_client.extract_fingerprint(FeatureExtractionRequest(data=sample_data))

        # find modalities by criminalId
        for modality in self.mongo_client.dp_criminal.modality.find({'criminalId': criminal_id}):
            if modality["type"] == "FINGERPRINT":
                match_request = MatchFingerprintsRequest(
                    sampleTemplate=MatchFingerprintTemplate(
                        keypointsSize=sample_fingerprint_extraction_response.keypointsSize,
                        encodedDescriptor=sample_fingerprint_extraction_response.encodedDescriptor
                    ),
                    realTemplate=MatchFingerprintTemplate(
                        keypointsSize=modality["keypointsSize"],
                        encodedDescriptor=modality["encodedDescriptor"]
                    )
                )

                match_response = self.matcher_client.match_fingerprints(match_request)
                match_score = match_response.matchScore

                if match_score_threshold <= match_score <= 100.0:
                    verified = True

        return VerificationResponse(verified=verified)
