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

    @GetMapping("{id}")
    @Operation(summary = "Get criminal with or without their modalities")
    fun getCriminalById(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = "false") withModalities: Boolean
    ) = criminalManagerClient.getCriminalById(id, withModalities)

    @PostMapping
    @Operation(summary = "Enroll new criminal with or without biometric data")
    fun enroll(@RequestBody criminalEnrollRequest: CriminalEnrollRequest) =
        criminalManagerClient.enroll(criminalEnrollRequest)

    @DeleteMapping("{id}")
    @Operation(summary = "Delete criminal with all their data")
    fun deleteCriminal(@PathVariable id: String) = criminalManagerClient.deleteCriminal(id)

    @DeleteMapping
    @Operation(summary = "Delete all criminals and modalities")
    fun deleteAll() = criminalManagerClient.deleteAll()

    @PostMapping("{id}/modalities")
    @Operation(summary = "Add modalities for criminal specified by ID")
    fun addModalitiesForCriminal(@PathVariable id: String, @RequestBody modalitiesRequest: AddModalitiesRequest) =
        criminalManagerClient.addModalitiesForCriminal(id, modalitiesRequest)

    @DeleteMapping("{id}/modalities")
    @Operation(summary = "Remove all modalities for criminal specified by ID")
    fun removeModalitiesOfCriminal(@PathVariable id: String) =
        criminalManagerClient.removeModalitiesOfCriminal(id)
}