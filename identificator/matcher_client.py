import grpc
import os

from proto.matcher_pb2 import MatchRequest, MatchResponse
from proto.matcher_pb2_grpc import MatcherStub


class MatcherClient:

    def __init__(self):
        matcher_host = os.getenv("DP_MATCHER_HOST", "localhost")
        matcher_port = os.getenv("DP_MATCHER_PORT", 50053)

        url = f'{matcher_host}:{matcher_port}'

        options = [
            ('grpc.max_message_length', 128 * 1024 * 1024),
            ('grpc.max_send_message_length', 128 * 1024 * 1024),
            ('grpc.max_receive_message_length', 128 * 1024 * 1024)
        ]

        self.channel = grpc.insecure_channel(url, options)
        self.stub = MatcherStub(channel=self.channel)

    def match_fingerprints(self, request: MatchRequest) -> MatchResponse:
        return self.stub.MatchFingerprints(request)

    def match_irises(self, request: MatchRequest) -> MatchResponse:
        return self.stub.MatchIrises(request)
