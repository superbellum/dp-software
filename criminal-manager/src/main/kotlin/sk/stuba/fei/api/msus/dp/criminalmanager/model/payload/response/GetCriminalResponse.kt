package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response

import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto.IModalityResponseDto

data class GetCriminalResponse(
    val criminal: CriminalResponseDto? = null,
    var modalities: List<IModalityResponseDto> = emptyList()
)