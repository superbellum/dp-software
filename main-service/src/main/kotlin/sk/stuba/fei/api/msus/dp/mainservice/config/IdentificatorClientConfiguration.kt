package sk.stuba.fei.api.msus.dp.mainservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class IdentificatorClientConfiguration(
    @Value("\${app.identificator-grpc-client.host}")
    val host: String,

    @Value("\${app.identificator-grpc-client.port}")
    val port: Int,

    @Value("\${app.identificator-grpc-client.default-identification-params.match-score-threshold}")
    val defaultIdentificationMatchScoreThreshold: Float,

    @Value("\${app.identificator-grpc-client.default-identification-params.candidate-count}")
    val defaultIdentificationCandidateCount: Long,

    @Value("\${app.identificator-grpc-client.default-verification-params.match-score-threshold}")
    val defaultVerificationMatchScoreThreshold: Float
)