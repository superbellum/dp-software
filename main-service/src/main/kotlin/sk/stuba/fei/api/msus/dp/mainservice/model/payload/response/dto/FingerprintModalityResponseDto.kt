package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.dto

import sk.stuba.fei.api.msus.dp.mainservice.model.FingerPosition
import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class FingerprintModalityResponseDto(
    override val id: String,
    override var criminalId: String,
    override val rawData: String,
    val position: FingerPosition
) : IModalityResponseDto {
    override val type = ModalityType.FINGERPRINT
}