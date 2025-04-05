package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response

import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto.IModalityResponseDto

data class GetCriminalModalitiesResponse(
    val criminalId: String,
    val modalities: List<IModalityResponseDto>? = null
)
