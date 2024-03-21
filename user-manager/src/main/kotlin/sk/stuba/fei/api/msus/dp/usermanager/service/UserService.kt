package sk.stuba.fei.api.msus.dp.usermanager.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.usermanager.model.User
import sk.stuba.fei.api.msus.dp.usermanager.payload.request.CreateUserRequest
import sk.stuba.fei.api.msus.dp.usermanager.payload.response.MessageResponse
import sk.stuba.fei.api.msus.dp.usermanager.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

    fun getByUsername(username: String) = userRepository.findByUsername(username)

    fun createUser(createUserRequest: CreateUserRequest): ResponseEntity<MessageResponse> {
        if (userRepository.existsByUsername(createUserRequest.username)) {
            return ResponseEntity.badRequest().body(MessageResponse("Username already exists"))
        }

        val newUser = User(
            username = createUserRequest.username,
            password = createUserRequest.password
        )

        userRepository.save(newUser)
        return ResponseEntity.ok(MessageResponse("New user created"))
    }

    fun deleteUserById(userId: Long): ResponseEntity<MessageResponse> {
        val userToDelete = userRepository.findById(userId)

        return if (userToDelete.isPresent) {
            userRepository.deleteById(userId)
            ResponseEntity.ok(MessageResponse("User with ID '$userId' deleted"))
        } else {
            ResponseEntity.badRequest().body(MessageResponse("User with ID '$userId' does not exist"))
        }
    }
}