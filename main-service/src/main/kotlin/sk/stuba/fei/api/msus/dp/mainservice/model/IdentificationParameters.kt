package sk.stuba.fei.api.msus.dp.mainservice.model

data class IdentificationParameters(
    val matchScoreThreshold: Float,
    val candidateCount: Long
)