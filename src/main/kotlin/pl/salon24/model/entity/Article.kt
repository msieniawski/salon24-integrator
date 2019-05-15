package pl.salon24.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ARTICLES")
data class Article(
        @Id
        val id: String,
        val title: String,
        @Column(columnDefinition = "TEXT")
        val content: String
)