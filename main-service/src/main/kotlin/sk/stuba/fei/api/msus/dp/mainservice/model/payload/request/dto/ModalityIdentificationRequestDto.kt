package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto

import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class ModalityIdentificationRequestDto(
    val type: ModalityType,
    val rawData: String
)
