package sk.stuba.fei.api.msus.dp.mainservice.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.IdentificationRequest
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.VerificationRequest
import sk.stuba.fei.api.msus.dp.mainservice.service.biom.BiometricsService

@CrossOrigin
@RestController
@RequestMapping("api/biometrics")
class BiometricsController(private val biometricsService: BiometricsService) {

    @PostMapping("identify")
    @Operation(summary = "Identify criminal by their modality")
    fun identify(@RequestBody identificationRequest: IdentificationRequest) =
        biometricsService.identify(identificationRequest)

    @PostMapping("verify")
    @Operation(summary = "Verify criminal by their modality and ID")
    fun verify(@RequestBody verificationRequest: VerificationRequest) = biometricsService.verify(verificationRequest)
}