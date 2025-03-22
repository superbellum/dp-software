package sk.stuba.fei.api.msus.dp.mainservice.service.crim

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.CriminalEnrollRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.GetAllCriminalsResponse
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.GetCriminalModalitiesResponse
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.GetCriminalResponse
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.MessageResponse

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
    fun enroll(@RequestBody criminalEnrollRequest: CriminalEnrollRequest): ResponseEntity<CriminalResponseDto>

    @DeleteMapping("{criminalId}")
    fun deleteCriminalById(@PathVariable criminalId: String): ResponseEntity<MessageResponse>

    @DeleteMapping
    fun deleteAll(): ResponseEntity<MessageResponse>

    @GetMapping("{criminalId}/modalities")
    fun getModalitiesForCriminal(@PathVariable criminalId: String): ResponseEntity<GetCriminalModalitiesResponse>

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