package sk.stuba.fei.api.msus.dp.criminalmanager.model.dto

import sk.stuba.fei.api.msus.dp.criminalmanager.model.entity.Address

data class CriminalResponseDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val address: Address
)