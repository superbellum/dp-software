package sk.stuba.fei.api.msus.dp.mainservice.model.dto

import io.swagger.annotations.ApiModelProperty
import sk.stuba.fei.api.msus.dp.mainservice.model.ModalityType

data class ModalityRequestDto(
    val type: ModalityType,
    @ApiModelProperty(value = "Base64 encoded image")
    val data: String
)