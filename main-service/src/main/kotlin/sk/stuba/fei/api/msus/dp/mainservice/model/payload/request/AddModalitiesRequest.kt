package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto.IModalityRequestDto

data class AddModalitiesRequest(
    val modalities: List<IModalityRequestDto>
)