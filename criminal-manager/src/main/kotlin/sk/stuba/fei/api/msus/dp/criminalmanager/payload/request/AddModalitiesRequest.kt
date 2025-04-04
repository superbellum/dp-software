package sk.stuba.fei.api.msus.dp.criminalmanager.payload.request

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.IModalityRequestDto

data class AddModalitiesRequest(
    val modalities: List<IModalityRequestDto>
)