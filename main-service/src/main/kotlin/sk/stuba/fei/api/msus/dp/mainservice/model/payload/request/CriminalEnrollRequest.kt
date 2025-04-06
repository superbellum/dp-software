package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto.CriminalRequestDto
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto.IModalityRequestDto

data class CriminalEnrollRequest(
    val criminal: CriminalRequestDto,
    val modalities: List<IModalityRequestDto> = emptyList()
)