package sk.stuba.fei.api.msus.dp.criminalmanager.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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

    @DeleteMapping("{id}")
    fun deleteCriminal(@PathVariable id: String) = criminalService.deleteCriminal(id)

    @DeleteMapping
    fun deleteAll() = criminalService.deleteAll()

    @PostMapping("{id}/modalities")
    fun addModalitiesForCriminal(@PathVariable id: String, @RequestBody modalitiesRequest: AddModalitiesRequest) =
        criminalService.addModalitiesForCriminal(id, modalitiesRequest)

    @DeleteMapping("{id}/modalities")
    fun removeModalitiesOfCriminal(@PathVariable id: String) = criminalService.removeModalitiesOfCriminal(id)
}