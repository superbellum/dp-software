package sk.stuba.fei.api.msus.dp.mainservice.model

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.CriminalResponse

data class CriminalCandidate(
    val criminal: CriminalResponse,
    val matchScore: Float
)