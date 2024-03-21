from concurrent.futures import ThreadPoolExecutor

import grpc
import os

from identificator_pb2_grpc import add_IdentificatorServicer_to_server
from identificator_service import IdentificatorService


def serve():
    identificator_port = os.getenv("DP_IDENTIFICATOR_PORT", 50052)

    options = [
        ('grpc.max_message_length', 128 * 1024 * 1024),
        ('grpc.max_send_message_length', 128 * 1024 * 1024),
        ('grpc.max_receive_message_length', 128 * 1024 * 1024)
    ]

    server = grpc.server(ThreadPoolExecutor(max_workers=10), options=options)
    add_IdentificatorServicer_to_server(IdentificatorService(), server)
    server.add_insecure_port(f'[::]:{identificator_port}')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    serve()
