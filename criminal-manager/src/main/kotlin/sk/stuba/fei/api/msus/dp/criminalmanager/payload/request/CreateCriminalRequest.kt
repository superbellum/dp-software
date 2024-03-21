package sk.stuba.fei.api.msus.dp.criminalmanager.payload.request

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.ModalityRequestDto

data class CreateCriminalRequest(
    val criminal: CriminalRequestDto,
    val modalities: List<ModalityRequestDto>? = null
)