package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.IdentificationParameters

data class IdentificationRequest(
    val modality: IModalityRequestDto,
    val identificationParameters: IdentificationParameters?
)