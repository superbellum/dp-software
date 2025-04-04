package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

data class CriminalEnrollRequest(
    val criminal: CriminalRequest,
    val modalities: List<IModalityRequestDto>? = null
)