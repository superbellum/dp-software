package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.IdentificationParameters
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto.ModalityIdentificationRequestDto

data class IdentificationRequest(
    val modality: ModalityIdentificationRequestDto,
    val identificationParameters: IdentificationParameters?
)