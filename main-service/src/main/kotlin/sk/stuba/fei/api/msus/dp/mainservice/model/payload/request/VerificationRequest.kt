package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.VerificationParameters
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto.ModalityVerificationRequestDto

data class VerificationRequest(
    val criminalId: String,
    val modality: ModalityVerificationRequestDto,
    val verificationParameters: VerificationParameters?
)