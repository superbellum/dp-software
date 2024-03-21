package sk.stuba.fei.api.msus.dp.mainservice.model

import sk.stuba.fei.api.msus.dp.identificator.Address as GrpcAddress

data class Address(
    val country: String,
    val city: String,
    val street: String? = "",
    val streetNumber: String? = "",
    val postCode: String? = ""
) {
    companion object {
        fun fromGrpcAddress(address: GrpcAddress) = Address(
            country = address.country,
            city = address.city,
            street = address.street,
            streetNumber = address.streetNumber,
            postCode = address.postCode
        )
    }
}