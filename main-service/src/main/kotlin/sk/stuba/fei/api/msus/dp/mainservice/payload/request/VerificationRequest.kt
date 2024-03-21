package sk.stuba.fei.api.msus.dp.mainservice.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.VerificationParameters
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.ModalityRequestDto

data class VerificationRequest(
    val criminalId: String,
    val modality: ModalityRequestDto,
    val verificationParameters: VerificationParameters?
)