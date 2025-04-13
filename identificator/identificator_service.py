import pymongo
from bson.objectid import ObjectId
import os

from feature_extractor_client import FeatureExtractorClient
from proto.feature_extractor_pb2 import FeatureExtractionRequest, ExtractionResponse
from proto.identificator_pb2 import IdentificationResponse, VerificationResponse, Candidate, Criminal, Address, \
    IdentificationRequest, VerificationRequest
from proto.identificator_pb2_grpc import IdentificatorServicer
from matcher_client import MatcherClient
from proto.matcher_pb2 import MatchRequest, MatchTemplate


class IdentificatorService(IdentificatorServicer):

    def __init__(self, *args, **kwargs):
        self.matcher_client = MatcherClient()
        self.feature_extractor_client = FeatureExtractorClient()
        mongodb_host = os.getenv('DP_MONGO_HOST', 'localhost')
        mongodb_port = int(os.getenv('DP_MONGO_PORT', '27117'))
        self.mongo_client = pymongo.MongoClient(mongodb_host, mongodb_port)
        print(f'Connected to MongoDB on {mongodb_host}:{mongodb_port}')

    def _get_criminal_from_db(self, criminal_id: str):
        return self.mongo_client.dp_criminal.criminal.find_one(ObjectId(criminal_id))

    @staticmethod
    def _create_candidate_from_criminal(db_criminal, match_score: float) -> Candidate:
        return Candidate(
            criminal=Criminal(
                id=str(db_criminal["_id"]),
                firstName=db_criminal["firstName"],
                lastName=db_criminal["lastName"],
                phoneNumber=db_criminal["phoneNumber"],
                email=db_criminal["email"],
                address=Address(
                    country=db_criminal["address"]["country"],
                    city=db_criminal["address"]["city"],
                    street=db_criminal["address"]["street"],
                    streetNumber=db_criminal["address"]["streetNumber"],
                    postCode=db_criminal["address"]["postCode"]
                )
            ),
            matchScore=match_score
        )

    def _extract_features(self, raw_data: str, modality_type: str) -> ExtractionResponse:
        extraction_request = FeatureExtractionRequest(rawData=raw_data)

        if modality_type == "FINGERPRINT":
            return self.feature_extractor_client.extract_fingerprint(extraction_request)
        elif modality_type == "IRIS":
            return self.feature_extractor_client.extract_iris(extraction_request)

    def _match_modality(self, match_request: MatchRequest, modality_type: str) -> float:
        if modality_type == "FINGERPRINT":
            return self.matcher_client.match_fingerprints(match_request).matchScore
        elif modality_type == "IRIS":
            return self.matcher_client.match_irises(match_request).matchScore

    def Identify(self, request: IdentificationRequest, context) -> IdentificationResponse:
        modality_type = request.modalityType
        sample_data = request.rawData
        match_score_threshold = request.identificationParameters.matchScoreThreshold
        candidate_count = request.identificationParameters.candidateCount

        sample_modality_extraction_response = self._extract_features(raw_data=sample_data, modality_type=modality_type)

        hitlist = []

        sample_match_template = MatchTemplate(
            keypointsSize=sample_modality_extraction_response.keypointsSize,
            encodedDescriptor=sample_modality_extraction_response.encodedDescriptor
        )

        # query criminal biometric data (modalities) from db
        modalities = list(self.mongo_client.dp_criminal.modality.find({'_class': modality_type}))

        if modalities is None or len(modalities) == 0:
            return IdentificationResponse(hitlist=[])

        for modality in modalities:
            match_request = MatchRequest(
                sampleTemplate=sample_match_template,
                realTemplate=MatchTemplate(
                    keypointsSize=modality["keypointsSize"],
                    encodedDescriptor=modality["encodedDescriptor"]
                )
            )

            match_score = self._match_modality(match_request=match_request, modality_type=modality_type)

            if match_score_threshold <= match_score <= 100.0:
                criminal = self._get_criminal_from_db(criminal_id=modality["criminalId"])

                if criminal is not None:
                    candidate = self._create_candidate_from_criminal(db_criminal=criminal, match_score=match_score)

                    hitlist.append(candidate)

        hitlist.sort(reverse=True, key=lambda c: c.matchScore)
        hitlist = hitlist[0:candidate_count]

        return IdentificationResponse(hitlist=hitlist)

    def Verify(self, request: VerificationRequest, context) -> VerificationResponse:
        modality_type = request.modalityType
        sample_data = request.rawData
        criminal_id = request.criminalId
        match_score_threshold = request.verificationParameters.matchScoreThreshold

        sample_modality_extraction_response = self._extract_features(raw_data=sample_data, modality_type=modality_type)

        sample_match_template = MatchTemplate(
            keypointsSize=sample_modality_extraction_response.keypointsSize,
            encodedDescriptor=sample_modality_extraction_response.encodedDescriptor
        )

        # find modalities by criminalId
        criminal_modalities = list(
            self.mongo_client.dp_criminal.modality.find({'criminalId': criminal_id, '_class': modality_type})
        )

        if criminal_modalities is None or len(criminal_modalities) == 0:
            return VerificationResponse(verified=False, matchScore=0.0)

        for modality in criminal_modalities:
            match_request = MatchRequest(
                sampleTemplate=sample_match_template,
                realTemplate=MatchTemplate(
                    keypointsSize=modality["keypointsSize"],
                    encodedDescriptor=modality["encodedDescriptor"]
                )
            )

            match_score = self._match_modality(match_request=match_request, modality_type=modality_type)

            if match_score_threshold <= match_score <= 100.0:
                return VerificationResponse(verified=True, matchScore=match_score)

        return VerificationResponse(verified=False, matchScore=0.0)
