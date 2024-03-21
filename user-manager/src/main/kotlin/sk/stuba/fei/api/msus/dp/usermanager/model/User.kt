package sk.stuba.fei.api.msus.dp.usermanager.model

import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Long? = null,

    @Column(unique = true)
    val username: String,

    val password: String,

    @CreationTimestamp
    val createdAt: Instant? = null
)