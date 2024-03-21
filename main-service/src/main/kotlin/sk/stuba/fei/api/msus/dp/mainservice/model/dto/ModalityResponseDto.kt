package sk.stuba.fei.api.msus.dp.mainservice.model.dto

import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class ModalityResponseDto(
    val id: String,
    var criminalId: String,
    val type: ModalityType,
    val data: String
)