package pl.salon24.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PROCESSED_SITES")
data class ProcessedSite(
        @Id
        val url: String
)