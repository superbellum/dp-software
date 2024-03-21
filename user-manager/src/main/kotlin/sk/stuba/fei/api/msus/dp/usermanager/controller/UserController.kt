package sk.stuba.fei.api.msus.dp.usermanager.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sk.stuba.fei.api.msus.dp.usermanager.payload.request.CreateUserRequest
import sk.stuba.fei.api.msus.dp.usermanager.service.UserService

@CrossOrigin
@RestController
@RequestMapping("api/users")
class UserController(private val userService: UserService) {

    @GetMapping("{username}")
    fun getUserByUsername(@PathVariable username: String) = userService.getByUsername(username)

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest) = userService.createUser(createUserRequest)

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteUserById(id)
}