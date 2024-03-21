package sk.stuba.fei.api.msus.dp.criminalmanager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class FeatureExtractorClientConfiguration(
    @Value("\${app.feature-extractor-grpc-client.host}")
    val host: String,

    @Value("\${app.feature-extractor-grpc-client.port}")
    val port: Int
)