package sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.FingerPosition
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType

data class FingerprintModalityRequestDto(
    override val rawData: String,
    val position: FingerPosition
) : IModalityRequestDto {
    override val type = ModalityType.FINGERPRINT
}
