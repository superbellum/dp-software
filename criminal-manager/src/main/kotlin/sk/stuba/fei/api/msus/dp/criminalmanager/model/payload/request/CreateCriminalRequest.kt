package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request

import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto.CriminalRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto.IModalityRequestDto

data class CreateCriminalRequest(
    val criminal: CriminalRequestDto,
    val modalities: List<IModalityRequestDto> = emptyList()
)