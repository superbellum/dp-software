package sk.stuba.fei.api.msus.dp.mainservice.model.payload.response

data class CriminalVerificationResponse(
    val verified: Boolean,
    val matchScore: Float
)