package sk.stuba.fei.api.msus.dp.criminalmanager.model.entity

import org.springframework.data.mongodb.core.mapping.Document
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.IModalityResponseDto

@Document(collection = "modality")
interface IModalityEntity {
    val id: String?
    val criminalId: String
    val type: ModalityType
    val rawData: String
    val keypointsSize: Int
    val encodedDescriptor: String
    fun toResponseDto(): IModalityResponseDto
}
