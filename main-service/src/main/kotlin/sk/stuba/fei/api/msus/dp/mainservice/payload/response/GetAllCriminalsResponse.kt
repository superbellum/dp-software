package sk.stuba.fei.api.msus.dp.mainservice.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.CriminalResponse

data class GetAllCriminalsResponse(
    val criminals: List<CriminalResponse>
)