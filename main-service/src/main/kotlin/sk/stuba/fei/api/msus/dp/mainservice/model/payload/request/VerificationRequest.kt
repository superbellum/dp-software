package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.VerificationParameters

data class VerificationRequest(
    val criminalId: String,
    val modality: IModalityRequestDto,
    val verificationParameters: VerificationParameters?
)