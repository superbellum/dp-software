from base64 import b64decode
from json import loads

import cv2
import numpy as np

from matcher_pb2 import MatchFingerprintsRequest, MatchResponse
from matcher_pb2_grpc import MatcherServicer


class MatcherService(MatcherServicer):

    def __init__(self, *args, **kwargs):
        pass

    @staticmethod
    def _decode(data):
        return loads(b64decode(data.encode()).decode())

    def MatchFingerprints(self, request: MatchFingerprintsRequest, context) -> MatchResponse:
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
