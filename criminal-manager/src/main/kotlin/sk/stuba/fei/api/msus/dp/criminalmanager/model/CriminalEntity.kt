package sk.stuba.fei.api.msus.dp.criminalmanager.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalResponseDto

@Document("criminal")
data class CriminalEntity(
    @Id
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val address: Address
) {
    fun toResponseDto() = CriminalResponseDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        address = address
    )
}