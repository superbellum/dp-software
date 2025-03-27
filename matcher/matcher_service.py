from base64 import b64decode
from json import loads

import cv2
import numpy as np

from matcher_pb2 import MatchRequest, MatchResponse
from matcher_pb2_grpc import MatcherServicer


class MatcherService(MatcherServicer):

    def __init__(self, *args, **kwargs):
        pass

    @staticmethod
    def _decode(data):
        return loads(b64decode(data.encode()).decode())

    def MatchFingerprints(self, request: MatchRequest, context) -> MatchResponse:
        print("matching fingerprints")

        sample_fingerprint_template = request.sampleTemplate
        real_fingerprint_template = request.realTemplate

        # decode descriptors
        sample_fingerprint_descriptor = self._decode(sample_fingerprint_template.encodedDescriptor)
        real_fingerprint_descriptor = self._decode(real_fingerprint_template.encodedDescriptor)

        # convert to numpy arrays
        sample_fingerprint_descriptor = np.array(sample_fingerprint_descriptor, dtype="float32")
        real_fingerprint_descriptor = np.array(real_fingerprint_descriptor, dtype="float32")

        # match templates by descriptors
        matcher = cv2.FlannBasedMatcher(dict(algorithm=1, trees=10), dict())
        matches = matcher.knnMatch(sample_fingerprint_descriptor, real_fingerprint_descriptor, k=2)

        match_points = []
        for p, q in matches:
            if p.distance < 0.1 * q.distance:
                match_points.append(p)

        keypoints_count = min(sample_fingerprint_template.keypointsSize, real_fingerprint_template.keypointsSize)

        # calculate match score
        match_score = len(match_points) / keypoints_count * 100

        return MatchResponse(matchScore=match_score)

    def MatchIrises(self, request: MatchRequest, context) -> MatchResponse:
        print("matching irises")

        sample_iris_template = request.sampleTemplate
        real_iris_template = request.realTemplate

        keypoints_count = max(sample_iris_template.keypointsSize, real_iris_template.keypointsSize)

        # decode descriptors
        sample_iris_descriptor = self._decode(sample_iris_template.encodedDescriptor)
        real_iris_descriptor = self._decode(real_iris_template.encodedDescriptor)

        # convert to numpy arrays
        sample_iris_descriptor = np.array(sample_iris_descriptor, dtype="float32")
        real_iris_descriptor = np.array(real_iris_descriptor, dtype="float32")

        matcher = cv2.FlannBasedMatcher(dict(algorithm=1, trees=5), dict(checks=50))
        matches = matcher.knnMatch(sample_iris_descriptor, real_iris_descriptor, k=2)

        good_matches = [m for m, n in matches if m.distance < 0.9 * n.distance]

        # calculate match score
        match_score = len(good_matches) / keypoints_count * 100

        return MatchResponse(matchScore=match_score)
