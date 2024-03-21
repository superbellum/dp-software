package sk.stuba.fei.api.msus.dp.usermanager.payload.request

data class LoginRequest(
    val username: String,
    val password: String
)