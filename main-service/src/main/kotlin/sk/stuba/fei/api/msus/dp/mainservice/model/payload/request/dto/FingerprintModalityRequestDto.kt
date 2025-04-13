package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto

import sk.stuba.fei.api.msus.dp.mainservice.model.FingerPosition
import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class FingerprintModalityRequestDto(
    override val rawData: String,
    val position: FingerPosition
) : IModalityRequestDto {
    override val type = ModalityType.FINGERPRINT
}
