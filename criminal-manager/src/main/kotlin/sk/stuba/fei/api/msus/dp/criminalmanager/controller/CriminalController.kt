package sk.stuba.fei.api.msus.dp.criminalmanager.controller

import org.springframework.web.bind.annotation.*
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.request.CreateCriminalRequest
import sk.stuba.fei.api.msus.dp.criminalmanager.service.CriminalService

@CrossOrigin
@RestController
@RequestMapping("api/criminals")
class CriminalController(private val criminalService: CriminalService) {

    @GetMapping("{id}")
    fun getCriminalById(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = "false") withModalities: Boolean
    ) = criminalService.getCriminalById(id, withModalities)

    @GetMapping
    fun getAllCriminals() = criminalService.getAllCriminals()

    @PostMapping
    fun createCriminal(@RequestBody createCriminalRequest: CreateCriminalRequest) =
        criminalService.createCriminal(createCriminalRequest)

    @DeleteMapping("{criminalId}")
    fun deleteCriminalById(@PathVariable criminalId: String) = criminalService.deleteCriminalById(criminalId)

    @DeleteMapping
    fun deleteAll() = criminalService.deleteAll()

    @GetMapping("{criminalId}/modalities")
    fun getModalitiesForCriminal(@PathVariable criminalId: String) =
        criminalService.getModalitiesForCriminal(criminalId)

    @PostMapping("{criminalId}/modalities")
    fun addModalitiesForCriminal(
        @PathVariable criminalId: String,
        @RequestBody modalitiesRequest: AddModalitiesRequest
    ) = criminalService.addModalitiesForCriminal(criminalId, modalitiesRequest)

    @DeleteMapping("{criminalId}/modalities")
    fun removeModalitiesOfCriminal(@PathVariable criminalId: String) =
        criminalService.removeModalitiesOfCriminal(criminalId)

    @DeleteMapping("{criminalId}/modalities/{modalityId}")
    fun removeModalityOfCriminal(@PathVariable criminalId: String, @PathVariable modalityId: String) =
        criminalService.removeModalityOfCriminal(criminalId, modalityId)
}