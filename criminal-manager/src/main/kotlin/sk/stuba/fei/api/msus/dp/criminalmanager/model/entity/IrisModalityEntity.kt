package sk.stuba.fei.api.msus.dp.criminalmanager.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import sk.stuba.fei.api.msus.dp.criminalmanager.model.IrisPosition
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.dto.IrisModalityResponseDto

@Document(collection = "modality")
data class IrisModalityEntity(
    @Id
    override var id: String? = null,
    override val criminalId: String,
    override val rawData: String,
    override val keypointsSize: Int,
    override val encodedDescriptor: String,
    val position: IrisPosition
) : IModalityEntity {
    override val type: ModalityType
        get() = ModalityType.IRIS

    override fun toResponseDto() = IrisModalityResponseDto(
        id = id!!,
        criminalId = criminalId,
        rawData = rawData,
        position = position
    )
}
