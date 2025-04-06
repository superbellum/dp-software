package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto

import sk.stuba.fei.api.msus.dp.mainservice.model.IrisPosition
import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class IrisModalityRequestDto(
    override val rawData: String,
    val position: IrisPosition
) : IModalityRequestDto {
    override val type: ModalityType
        get() = ModalityType.IRIS
}
