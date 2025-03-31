package sk.stuba.fei.api.msus.dp.criminalmanager.service

import io.grpc.ManagedChannelBuilder
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.criminalmanager.config.FeatureExtractorClientConfiguration
import sk.stuba.fei.api.msus.dp.featureextractor.ExtractionResponse
import sk.stuba.fei.api.msus.dp.featureextractor.FeatureExtractionRequest
import sk.stuba.fei.api.msus.dp.featureextractor.FeatureExtractorGrpc

@Service
class FeatureExtractorClient(private val configuration: FeatureExtractorClientConfiguration) {
    private val stub = FeatureExtractorGrpc.newBlockingStub(
        ManagedChannelBuilder
            .forAddress(configuration.host, configuration.port)
            .usePlaintext()
            .build()
    )

    fun extractFingerprint(request: FeatureExtractionRequest): ExtractionResponse = stub.extractFingerprint(request)

    fun extractIris(request: FeatureExtractionRequest): ExtractionResponse = stub.extractIris(request)
}