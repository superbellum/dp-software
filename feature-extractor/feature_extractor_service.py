from base64 import b64encode, b64decode
from io import BytesIO
from json import dumps

import cv2
from PIL import Image
from numpy import array

from proto.feature_extractor_pb2 import FeatureExtractionRequest, ExtractionResponse
from proto.feature_extractor_pb2_grpc import FeatureExtractorServicer
from numpy_array_encoder import NumpyArrayEncoder


class FeatureExtractorService(FeatureExtractorServicer):

    def __init__(self, *args, **kwargs):
        pass

    @staticmethod
    def _encode_numpy_array(obj) -> str:
        return b64encode(dumps(obj, cls=NumpyArrayEncoder).encode()).decode()

    def ExtractFingerprint(self, request: FeatureExtractionRequest, context) -> ExtractionResponse:
        print('extracting fingerprint')

        data = request.rawData
        data_decoded = b64decode(data)
        raw_image = Image.open(BytesIO(data_decoded))

        fingerprint_image = cv2.cvtColor(array(raw_image), cv2.COLOR_RGB2BGR)
        sift = cv2.SIFT_create()
        keypoints, descriptors = sift.detectAndCompute(fingerprint_image, None)

        return ExtractionResponse(
            keypointsSize=len(keypoints),
            encodedDescriptor=self._encode_numpy_array(descriptors)
        )

    def ExtractIris(self, request: FeatureExtractionRequest, context) -> ExtractionResponse:
        print('extracting iris')

        data = request.rawData
        data_decoded = b64decode(data)
        raw_image = Image.open(BytesIO(data_decoded))
        raw_image = cv2.resize(array(raw_image), (64, 64))

        sift = cv2.SIFT_create()
        keypoints, descriptors = sift.detectAndCompute(raw_image, None)

        return ExtractionResponse(
            keypointsSize=len(keypoints),
            encodedDescriptor=self._encode_numpy_array(descriptors)
        )
