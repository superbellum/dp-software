package sk.stuba.fei.api.msus.dp.mainservice.model

data class Modality(
    val id: String? = null,
    var criminalId: String? = null,
    val type: ModalityType,
    val data: String
)