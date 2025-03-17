package sk.stuba.fei.api.msus.dp.mainservice.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import feign.FeignException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import sk.stuba.fei.api.msus.dp.mainservice.payload.response.MessageResponse

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(e: FeignException): ResponseEntity<MessageResponse> {
        val errorMessage = e.responseBody()
            .map { String(it.array()) }
            .orElse("""{"message": "Unknown error occurred"}""")

        val messageResponse = jacksonObjectMapper().readValue<MessageResponse>(errorMessage)

        return ResponseEntity.status(e.status()).body(messageResponse)
    }
}
