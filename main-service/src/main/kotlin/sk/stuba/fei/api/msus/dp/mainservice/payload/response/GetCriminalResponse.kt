package sk.stuba.fei.api.msus.dp.mainservice.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.CriminalResponse
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.ModalityResponse

data class GetCriminalResponse(
    val criminal: CriminalResponse?,
    val modalities: List<ModalityResponse>? = null
)