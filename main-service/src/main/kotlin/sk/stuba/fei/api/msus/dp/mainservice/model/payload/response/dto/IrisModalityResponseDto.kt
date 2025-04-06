package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.dto

import sk.stuba.fei.api.msus.dp.mainservice.model.IrisPosition
import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class IrisModalityResponseDto(
    override val id: String,
    override var criminalId: String,
    override val rawData: String,
    val position: IrisPosition
) : IModalityResponseDto {
    override val type: ModalityType
        get() = ModalityType.IRIS
}
