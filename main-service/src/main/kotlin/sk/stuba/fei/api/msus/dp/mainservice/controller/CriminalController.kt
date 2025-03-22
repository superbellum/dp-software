package sk.stuba.fei.api.msus.dp.mainservice.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.CriminalEnrollRequest
import sk.stuba.fei.api.msus.dp.mainservice.service.crim.CriminalManagerClient

@CrossOrigin
@RestController
@RequestMapping("api/criminals")
class CriminalController(private val criminalManagerClient: CriminalManagerClient) {

    @GetMapping("{criminalId}")
    @Operation(summary = "Get criminal by ID with or without their modalities")
    fun getCriminalById(
        @PathVariable criminalId: String,
        @RequestParam(required = false, defaultValue = "false") withModalities: Boolean
    ) = criminalManagerClient.getCriminalById(criminalId, withModalities)

    @GetMapping
    @Operation(summary = "Get all criminals without their modalities")
    fun getAllCriminals() = criminalManagerClient.getAllCriminals()

    @PostMapping
    @Operation(summary = "Enroll new criminal with or without biometric data")
    fun enroll(@RequestBody criminalEnrollRequest: CriminalEnrollRequest) =
        criminalManagerClient.enroll(criminalEnrollRequest)

    @DeleteMapping("{criminalId}")
    @Operation(summary = "Delete criminal by ID with all their data")
    fun deleteCriminalById(@PathVariable criminalId: String) = criminalManagerClient.deleteCriminalById(criminalId)

    @DeleteMapping
    @Operation(summary = "Delete all criminals and modalities")
    fun deleteAll() = criminalManagerClient.deleteAll()

    @GetMapping("{criminalId}/modalities")
    @Operation(summary = "Get all modalities for criminal specified by ID")
    fun getModalitiesForCriminal(@PathVariable criminalId: String) =
        criminalManagerClient.getModalitiesForCriminal(criminalId)

    @PostMapping("{criminalId}/modalities")
    @Operation(summary = "Add modalities for criminal specified by ID")
    fun addModalitiesForCriminal(@PathVariable criminalId: String, @RequestBody modalitiesRequest: AddModalitiesRequest) =
        criminalManagerClient.addModalitiesForCriminal(criminalId, modalitiesRequest)

    @DeleteMapping("{criminalId}/modalities")
    @Operation(summary = "Remove all modalities for criminal specified by ID")
    fun removeModalitiesOfCriminal(@PathVariable criminalId: String) =
        criminalManagerClient.removeModalitiesOfCriminal(criminalId)

    @DeleteMapping("{criminalId}/modalities/{modalityId}")
    fun removeModalityOfCriminal(@PathVariable criminalId: String, @PathVariable modalityId: String) =
        criminalManagerClient.removeModalityOfCriminal(criminalId, modalityId)
}