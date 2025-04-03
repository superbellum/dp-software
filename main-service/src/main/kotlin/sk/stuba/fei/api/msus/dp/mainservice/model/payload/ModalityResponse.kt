package sk.stuba.fei.api.msus.dp.mainservice.model.payload

import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class ModalityResponse(
    val id: String,
    var criminalId: String,
    val type: ModalityType,
    val data: String
)