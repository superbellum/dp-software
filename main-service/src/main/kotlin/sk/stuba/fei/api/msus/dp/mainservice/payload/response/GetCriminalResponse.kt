package sk.stuba.fei.api.msus.dp.mainservice.payload.response

import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.ModalityResponseDto

data class GetCriminalResponse(
    val criminal: CriminalResponseDto?,
    val modalities: List<ModalityResponseDto>? = null
)