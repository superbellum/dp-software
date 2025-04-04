package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

data class AddModalitiesRequest(
    val modalities: List<IModalityRequestDto>
)