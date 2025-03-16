package sk.stuba.fei.api.msus.dp.mainservice.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalResponseDto

data class GetAllCriminalsResponse(
    val criminals: List<CriminalResponseDto>
)