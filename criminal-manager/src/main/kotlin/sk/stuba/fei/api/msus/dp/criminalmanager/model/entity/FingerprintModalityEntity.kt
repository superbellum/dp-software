package sk.stuba.fei.api.msus.dp.criminalmanager.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import sk.stuba.fei.api.msus.dp.criminalmanager.model.FingerPosition
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto.FingerprintModalityResponseDto

@Document(collection = "modality")
@TypeAlias("FINGERPRINT")
data class FingerprintModalityEntity(
    @Id
    override var id: String? = null,
    override val criminalId: String,
    override val rawData: String,
    override val keypointsSize: Int,
    override val encodedDescriptor: String,
    val position: FingerPosition
) : IModalityEntity {
    override val type: ModalityType
        get() = ModalityType.FINGERPRINT

    override fun toResponseDto() = FingerprintModalityResponseDto(
        id = id!!,
        criminalId = criminalId,
        rawData = rawData,
        position = position
    )
}
