package sk.stuba.fei.api.msus.dp.criminalmanager.model.entity

data class Address(
    val country: String,
    val city: String,
    val street: String? = "",
    val streetNumber: String? = "",
    val postCode: String? = ""
)