package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request

import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto.IModalityRequestDto

data class AddModalitiesRequest(
    val modalities: List<IModalityRequestDto>
)