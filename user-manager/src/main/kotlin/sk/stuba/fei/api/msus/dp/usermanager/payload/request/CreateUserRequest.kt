package sk.stuba.fei.api.msus.dp.usermanager.payload.request

data class CreateUserRequest(
    val username: String,
    val password: String
)