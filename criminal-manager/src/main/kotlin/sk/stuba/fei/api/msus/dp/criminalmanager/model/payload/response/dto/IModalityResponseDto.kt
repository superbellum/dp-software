package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

interface IModalityResponseDto {
    val id: String
    var criminalId: String
    val type: ModalityType
    val rawData: String
}