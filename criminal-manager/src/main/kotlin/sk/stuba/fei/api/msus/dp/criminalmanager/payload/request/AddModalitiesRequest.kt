package sk.stuba.fei.api.msus.dp.criminalmanager.payload.request

import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.ModalityRequestDto

data class AddModalitiesRequest(
    val modalities: List<ModalityRequestDto>
)