package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.FingerPosition
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

data class FingerprintModalityResponseDto(
    override val id: String,
    override var criminalId: String,
    override val rawData: String,
    val position: FingerPosition
) : IModalityResponseDto {
    override val type: ModalityType
        get() = ModalityType.FINGERPRINT
}