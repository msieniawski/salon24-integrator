package pl.salon24.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USERS")
data class User(
        @Id
        val id: String,
        val url: String,
        val name: String,
        val imageUrl: String
)