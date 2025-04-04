package sk.stuba.fei.api.msus.dp.mainservice.model.payload.request

import sk.stuba.fei.api.msus.dp.mainservice.model.Address

data class CriminalRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String? = "",
    val email: String? = "",
    val address: Address
)