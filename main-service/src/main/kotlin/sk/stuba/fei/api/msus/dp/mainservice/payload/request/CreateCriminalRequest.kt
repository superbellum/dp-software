package sk.stuba.fei.api.msus.dp.mainservice.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalRequestDto
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.ModalityRequestDto

data class CreateCriminalRequest(
    val criminal: CriminalRequestDto,
    val modalities: List<ModalityRequestDto>? = null
)