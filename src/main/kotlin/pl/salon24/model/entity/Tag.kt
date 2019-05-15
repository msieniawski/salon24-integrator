package pl.salon24.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TAGS")
data class Tag(
        @Id
        val id: String,
        val tag: String
)