package sk.stuba.fei.api.msus.dp.criminalmanager.model.exceptions

data class UnsupportedModalityTypeException(
    override val message: String = "Unsupported modality type"
) : Exception()
