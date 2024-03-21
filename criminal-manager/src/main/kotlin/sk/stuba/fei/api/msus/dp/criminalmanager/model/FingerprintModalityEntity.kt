package sk.stuba.fei.api.msus.dp.criminalmanager.model

import org.springframework.data.mongodb.core.mapping.Document
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType.FINGERPRINT

@Document(collection = "modality")
class FingerprintModalityEntity(
    criminalId: String,
    rawData: String,
    val keypointsSize: Int,
    val encodedDescriptor: String
) : ModalityEntity(
    criminalId = criminalId,
    type = FINGERPRINT,
    rawData = rawData
)
