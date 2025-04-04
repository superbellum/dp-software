package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.IrisPosition
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

data class IrisModalityRequestDto(
    override val rawData: String,
    val position: IrisPosition
) : IModalityRequestDto {
    override val type: ModalityType
        get() = ModalityType.IRIS
}
