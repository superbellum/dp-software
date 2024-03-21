package sk.stuba.fei.api.msus.dp.usermanager.repository

import org.springframework.data.jpa.repository.JpaRepository
import sk.stuba.fei.api.msus.dp.usermanager.model.User
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String?): Optional<User>

    fun existsByUsername(username: String?): Boolean
}