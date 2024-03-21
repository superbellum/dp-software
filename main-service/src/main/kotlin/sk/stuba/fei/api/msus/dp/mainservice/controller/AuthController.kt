package sk.stuba.fei.api.msus.dp.mainservice.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("api/auth")
class AuthController() { // todo

//    @PostMapping(value = ["login"], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun login(@RequestBody loginRequest: LoginRequest) = authService.login(loginRequest)
//
//    @PostMapping(value = ["logout"], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun logout(@RequestBody refreshTokenRequest: RefreshTokenRequest) = authService.logout(refreshTokenRequest)
//
//    @PostMapping(value = ["signup"], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun signup(@RequestBody signupRequest: SignupRequest) = authService.signup(signupRequest)
//
//    @GetMapping(value = ["accountVerification/{token}"])
//    fun verifyAccount(@PathVariable token: String) = authService.verifyAccount(token)
//
//    @PostMapping(value = ["refreshToken"], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest) =
//        authService.refreshToken(refreshTokenRequest)
}