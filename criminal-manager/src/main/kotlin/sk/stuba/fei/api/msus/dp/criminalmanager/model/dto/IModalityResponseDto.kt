package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

interface IModalityResponseDto {
    val id: String
    var criminalId: String
    val type: ModalityType
    val rawData: String
}