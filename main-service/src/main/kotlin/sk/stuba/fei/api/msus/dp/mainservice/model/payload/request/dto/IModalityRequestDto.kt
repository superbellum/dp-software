package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = FingerprintModalityRequestDto::class, name = "FINGERPRINT"),
    JsonSubTypes.Type(value = IrisModalityRequestDto::class, name = "IRIS")
)
interface IModalityRequestDto {
    val type: ModalityType
    val rawData: String
}