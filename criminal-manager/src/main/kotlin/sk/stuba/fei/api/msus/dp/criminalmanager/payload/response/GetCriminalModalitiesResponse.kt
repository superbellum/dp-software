package sk.stuba.fei.api.msus.dp.criminalmanager.payload.response

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.ModalityResponseDto

data class GetCriminalModalitiesResponse(
    val criminalId: String,
    val modalities: List<ModalityResponseDto>? = null
)
