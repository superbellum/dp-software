import grpc
import os

from concurrent.futures import ThreadPoolExecutor
from feature_extractor_pb2_grpc import add_FeatureExtractorServicer_to_server
from feature_extractor_service import FeatureExtractorService


def serve():
    feature_extractor_port = os.getenv("DP_FEATURE_EXTRACTOR_PORT", 50051)

    options = [
        ('grpc.max_message_length', 128 * 1024 * 1024),
        ('grpc.max_send_message_length', 128 * 1024 * 1024),
        ('grpc.max_receive_message_length', 128 * 1024 * 1024)
    ]

    server = grpc.server(ThreadPoolExecutor(max_workers=10), options=options)
    add_FeatureExtractorServicer_to_server(FeatureExtractorService(), server)
    server.add_insecure_port(f'[::]:{feature_extractor_port}')
    server.start()

    print(f'Serving on port {feature_extractor_port}')

    server.wait_for_termination()


if __name__ == '__main__':
    print('Running main()')
    serve()
