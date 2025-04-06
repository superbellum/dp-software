package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.dto.IModalityResponseDto

data class GetCriminalModalitiesResponse(
    val criminalId: String,
    val modalities: List<IModalityResponseDto>? = null
)
