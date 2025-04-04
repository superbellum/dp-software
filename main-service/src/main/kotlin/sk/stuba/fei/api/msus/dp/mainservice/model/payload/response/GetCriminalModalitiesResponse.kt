package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

data class GetCriminalModalitiesResponse(
    val criminalId: String,
    val modalities: List<IModalityResponseDto>? = null
)
