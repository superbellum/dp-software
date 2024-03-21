package sk.stuba.fei.api.msus.dp.mainservice.model.dto

import sk.stuba.fei.api.msus.dp.identificator.Criminal
import sk.stuba.fei.api.msus.dp.mainservice.model.Address

data class CriminalResponseDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val address: Address
) {
    companion object {
        fun fromGrpcCriminal(criminal: Criminal) = CriminalResponseDto(
            id = criminal.id,
            firstName = criminal.firstName,
            lastName = criminal.lastName,
            phoneNumber = criminal.phoneNumber,
            email = criminal.email,
            address = Address.fromGrpcAddress(criminal.address)
        )
    }
}