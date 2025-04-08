package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.dto.IModalityResponseDto

data class GetCriminalResponse(
    val criminal: CriminalResponseDto? = null,
    val modalities: List<IModalityResponseDto> = emptyList()
)