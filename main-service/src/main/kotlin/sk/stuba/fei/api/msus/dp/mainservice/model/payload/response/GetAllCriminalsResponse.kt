package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

data class GetAllCriminalsResponse(
    val criminals: List<CriminalResponse>
)