package sk.stuba.fei.api.msus.dp.mainservice.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.CriminalRequest
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.ModalityRequest

data class CriminalEnrollRequest(
    val criminal: CriminalRequest,
    val modalities: List<ModalityRequest>? = null
)