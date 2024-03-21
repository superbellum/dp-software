package sk.stuba.fei.api.msus.dp.mainservice.service.biom

import io.grpc.ManagedChannelBuilder
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.identificator.IdentificationParameters
import sk.stuba.fei.api.msus.dp.identificator.IdentificationResponse
import sk.stuba.fei.api.msus.dp.identificator.IdentificatorGrpc
import sk.stuba.fei.api.msus.dp.identificator.VerificationParameters
import sk.stuba.fei.api.msus.dp.identificator.VerificationResponse
import sk.stuba.fei.api.msus.dp.mainservice.config.IdentificatorClientConfiguration
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.IdentificationRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.VerificationRequest
import sk.stuba.fei.api.msus.dp.identificator.IdentificationRequest as GrpcIdentificationRequest
import sk.stuba.fei.api.msus.dp.identificator.VerificationRequest as GrpcVerificationRequest

@Service
class IdentificatorClient(private val configuration: IdentificatorClientConfiguration) {
    private val stub = IdentificatorGrpc.newBlockingStub(
        ManagedChannelBuilder
            .forAddress(configuration.host, configuration.port)
            .usePlaintext()
            .build()
    )

    fun identify(identificationRequest: IdentificationRequest): IdentificationResponse? {
        val grpcIdentificationParams = IdentificationParameters.newBuilder()
            .setMatchScoreThreshold(
                identificationRequest.identificationParameters
                    ?.matchScoreThreshold
                    ?: configuration.defaultIdentificationMatchScoreThreshold
            )
            .setCandidateCount(
                identificationRequest.identificationParameters
                    ?.candidateCount
                    ?: configuration.defaultIdentificationCandidateCount
            )
            .build()

        val grpcIdentificationRequest = GrpcIdentificationRequest.newBuilder()
            .setModalityType(identificationRequest.modality.type.toString())
            .setData(identificationRequest.modality.data)
            .setIdentificationParameters(grpcIdentificationParams)
            .build()

        return stub.identify(grpcIdentificationRequest)
    }

    fun verify(verificationRequest: VerificationRequest): VerificationResponse? {
        val grpcVerificationParams = VerificationParameters.newBuilder()
            .setMatchScoreThreshold(
                verificationRequest.verificationParameters
                    ?.matchScoreThreshold
                    ?: configuration.defaultVerificationMatchScoreThreshold
            ).build()

        val grpcVerificationRequest = GrpcVerificationRequest.newBuilder()
            .setModalityType(verificationRequest.modality.type.toString())
            .setData(verificationRequest.modality.data)
            .setCriminalId(verificationRequest.criminalId)
            .setVerificationParameters(grpcVerificationParams)
            .build()

        return stub.verify(grpcVerificationRequest)
    }
}