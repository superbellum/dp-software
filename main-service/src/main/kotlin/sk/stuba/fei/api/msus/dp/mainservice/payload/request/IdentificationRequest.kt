package sk.stuba.fei.api.msus.dp.mainservice.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.IdentificationParameters
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.ModalityRequestDto

data class IdentificationRequest(
    val modality: ModalityRequestDto,
    val identificationParameters: IdentificationParameters?
)