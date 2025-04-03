package sk.stuba.fei.api.msus.dp.mainservice.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.IdentificationParameters
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.ModalityRequest

data class IdentificationRequest(
    val modality: ModalityRequest,
    val identificationParameters: IdentificationParameters?
)