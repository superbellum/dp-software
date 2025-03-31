package sk.stuba.fei.api.msus.dp.mainservice.service.biom

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.mainservice.model.CriminalCandidate
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.IdentificationRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.VerificationRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.CriminalIdentificationResponse
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.CriminalVerificationResponse

@Service
class BiometricsService(private val identificatorClient: IdentificatorClient) {
    fun identify(identificationRequest: IdentificationRequest): ResponseEntity<CriminalIdentificationResponse> {
        val hitlist = identificatorClient.identify(identificationRequest)
            ?.hitlistList
            ?: return ResponseEntity.ok(CriminalIdentificationResponse())

        val responseHitlist = hitlist.mapNotNull {
            CriminalCandidate(
                criminal = CriminalResponseDto.fromGrpcCriminal(it.criminal),
                matchScore = it.matchScore
            )
        }

        return ResponseEntity.ok(CriminalIdentificationResponse(responseHitlist))
    }

    fun verify(verificationRequest: VerificationRequest): ResponseEntity<CriminalVerificationResponse> {
        val verificationResponse = identificatorClient.verify(verificationRequest)

        return ResponseEntity.ok(
            CriminalVerificationResponse(
                verified = verificationResponse.verified,
                matchScore = verificationResponse.matchScore
            )
        )
    }
}