package sk.stuba.fei.api.msus.dp.criminalmanager.payload.request

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.IModalityRequestDto

data class CreateCriminalRequest(
    val criminal: CriminalRequestDto,
    val modalities: List<IModalityRequestDto> = emptyList()
)