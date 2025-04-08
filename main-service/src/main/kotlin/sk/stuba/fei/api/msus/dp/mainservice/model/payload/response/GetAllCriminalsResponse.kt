package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.dto.CriminalResponseDto

data class GetAllCriminalsResponse(
    val criminals: List<CriminalResponseDto>
)