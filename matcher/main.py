from concurrent.futures import ThreadPoolExecutor

import grpc
import os

from matcher_pb2_grpc import add_MatcherServicer_to_server
from matcher_service import MatcherService


def serve():
    matcher_port = os.getenv("DP_MATCHER_PORT", 50053)

    options = [
        ('grpc.max_message_length', 128 * 1024 * 1024),
        ('grpc.max_send_message_length', 128 * 1024 * 1024),
        ('grpc.max_receive_message_length', 128 * 1024 * 1024)
    ]

    server = grpc.server(ThreadPoolExecutor(max_workers=10), options=options)
    add_MatcherServicer_to_server(MatcherService(), server)
    server.add_insecure_port(f'[::]:{matcher_port}')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    serve()
