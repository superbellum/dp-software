package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

data class ModalityResponseDto(
    val id: String,
    var criminalId: String,
    val type: ModalityType,
    val data: String
)