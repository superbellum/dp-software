package sk.stuba.fei.api.msus.dp.mainservice.model

import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalResponseDto

data class CriminalCandidate(
    val criminal: CriminalResponseDto,
    val matchScore: Float
)