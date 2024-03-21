package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.Address
import sk.stuba.fei.api.msus.dp.criminalmanager.model.CriminalEntity

data class CriminalRequestDto(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String? = "",
    val email: String? = "",
    val address: Address
) {
    fun toEntity() = CriminalEntity(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        address = address
    )
}