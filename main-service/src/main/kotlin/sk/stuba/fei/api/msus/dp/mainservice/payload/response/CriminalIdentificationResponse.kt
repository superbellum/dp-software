package sk.stuba.fei.api.msus.dp.mainservice.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.CriminalCandidate

data class CriminalIdentificationResponse(
    val hitlist: List<CriminalCandidate>? = null
)