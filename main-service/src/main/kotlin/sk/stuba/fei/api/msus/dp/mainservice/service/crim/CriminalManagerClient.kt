package sk.stuba.fei.api.msus.dp.mainservice.service.crim

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import sk.stuba.fei.api.msus.dp.mainservice.model.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.request.CriminalEnrollRequest
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.GetCriminalResponse
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.MessageResponse

@FeignClient(name = "criminal-manager", url = "\${app.criminal-manager.url}")
interface CriminalManagerClient {

    @GetMapping("{id}")
    fun getCriminalById(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = "false") withModalities: Boolean
    ): ResponseEntity<GetCriminalResponse>

    @PostMapping
    fun enroll(@RequestBody criminalEnrollRequest: CriminalEnrollRequest): ResponseEntity<CriminalResponseDto>

    @DeleteMapping("{id}")
    fun deleteCriminal(@PathVariable id: String): ResponseEntity<MessageResponse>

    @DeleteMapping
    fun deleteAll(): ResponseEntity<MessageResponse>

    @PostMapping("{id}/modalities")
    fun addModalitiesForCriminal(
        @PathVariable id: String,
        @RequestBody modalitiesRequest: AddModalitiesRequest
    ): ResponseEntity<MessageResponse>

    @DeleteMapping("{id}/modalities")
    fun removeModalitiesOfCriminal(@PathVariable id: String): ResponseEntity<MessageResponse>
}