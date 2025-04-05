package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response

import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto.CriminalResponseDto

data class GetAllCriminalsResponse(
    val criminals: List<CriminalResponseDto>
)