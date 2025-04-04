package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

interface IModalityResponseDto {
    val id: String
    var criminalId: String
    val type: ModalityType
    val rawData: String
}