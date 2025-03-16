package sk.stuba.fei.api.msus.dp.criminalmanager.payload.response

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalResponseDto

data class GetAllCriminalsResponse(
    val criminals: List<CriminalResponseDto>
)