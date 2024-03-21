package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

data class ModalityRequestDto(
    val type: ModalityType,
    val data: String
)