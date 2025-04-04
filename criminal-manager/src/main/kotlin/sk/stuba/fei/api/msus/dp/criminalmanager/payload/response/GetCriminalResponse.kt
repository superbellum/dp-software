package sk.stuba.fei.api.msus.dp.criminalmanager.payload.response

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.IModalityResponseDto

data class GetCriminalResponse(
    val criminal: CriminalResponseDto?,
    val modalities: List<IModalityResponseDto>? = null
)