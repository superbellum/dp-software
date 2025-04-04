package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

data class GetCriminalResponse(
    val criminal: CriminalResponse?,
    val modalities: List<IModalityResponseDto>? = null
)