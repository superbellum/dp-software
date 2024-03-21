package sk.stuba.fei.api.msus.dp.mainservice.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.dto.ModalityRequestDto

data class AddModalitiesRequest(
    val modalities: List<ModalityRequestDto>
)