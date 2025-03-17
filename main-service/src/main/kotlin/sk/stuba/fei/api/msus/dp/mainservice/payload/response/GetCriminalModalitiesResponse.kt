package sk.stuba.fei.api.msus.dp.mainservice.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.dto.ModalityResponseDto

data class GetCriminalModalitiesResponse(
    val criminalId: String,
    val modalities: List<ModalityResponseDto>? = null
)
