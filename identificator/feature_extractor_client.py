import grpc
import os

from feature_extractor_pb2_grpc import FeatureExtractorStub


class FeatureExtractorClient:

    def __init__(self):
        feature_extractor_host = os.getenv("DP_FEATURE_EXTRACTOR_HOST", "localhost")
        feature_extractor_port = os.getenv("DP_FEATURE_EXTRACTOR_PORT", 50051)

        url = f'{feature_extractor_host}:{feature_extractor_port}'

        options = [
            ('grpc.max_message_length', 128 * 1024 * 1024),
            ('grpc.max_send_message_length', 128 * 1024 * 1024),
            ('grpc.max_receive_message_length', 128 * 1024 * 1024)
        ]

        self.channel = grpc.insecure_channel(url, options)
        self.stub = FeatureExtractorStub(channel=self.channel)

    def extract_fingerprint(self, request):
        return self.stub.ExtractFingerprint(request)
