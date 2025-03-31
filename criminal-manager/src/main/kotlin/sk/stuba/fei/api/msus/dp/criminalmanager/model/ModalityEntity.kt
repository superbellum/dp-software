package sk.stuba.fei.api.msus.dp.criminalmanager.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.ModalityResponseDto

@Document(collection = "modality")
open class ModalityEntity(
    @Id
    var id: String? = null,
    var criminalId: String,
    val type: ModalityType,
    val rawData: String,
    val keypointsSize: Int,
    val encodedDescriptor: String
) {
    fun toResponseDto() = ModalityResponseDto(
        id = id!!,
        criminalId = criminalId,
        type = type,
        data = rawData
    )
}