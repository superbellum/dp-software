package sk.stuba.fei.api.msus.dp.mainservice.service.crim

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.request.CriminalEnrollRequest
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.GetAllCriminalsResponse
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.GetCriminalResponse
import sk.stuba.fei.api.msus.dp.mainservice.model.payload.response.MessageResponse

@FeignClient(name = "criminal-manager", url = "\${app.criminal-manager.url}")
interface CriminalManagerClient {

    @GetMapping("{id}")
    fun getCriminalById(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = "false") withModalities: Boolean
    ): ResponseEntity<GetCriminalResponse>

    @GetMapping
    fun getAllCriminals(): ResponseEntity<GetAllCriminalsResponse>

    @PostMapping
    fun enroll(@RequestBody criminalEnrollRequest: CriminalEnrollRequest): ResponseEntity<out Any>

    @DeleteMapping("{criminalId}")
    fun deleteCriminalById(@PathVariable criminalId: String): ResponseEntity<MessageResponse>

    @DeleteMapping
    fun deleteAll(): ResponseEntity<MessageResponse>

    @GetMapping("{criminalId}/modalities")
    fun getModalitiesForCriminal(@PathVariable criminalId: String): ResponseEntity<out Any>

    @PostMapping("{criminalId}/modalities")
    fun addModalitiesForCriminal(
        @PathVariable criminalId: String,
        @RequestBody modalitiesRequest: AddModalitiesRequest
    ): ResponseEntity<MessageResponse>

    @DeleteMapping("{criminalId}/modalities")
    fun removeModalitiesOfCriminal(@PathVariable criminalId: String): ResponseEntity<MessageResponse>

    @DeleteMapping("{criminalId}/modalities/{modalityId}")
    fun removeModalityOfCriminal(
        @PathVariable criminalId: String,
        @PathVariable modalityId: String
    ): ResponseEntity<MessageResponse>
}