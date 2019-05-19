package pl.salon24.model.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "ARTICLES")
data class Article(
        @Id
        val id: String,
        val url: String,
        val title: String,
        @OneToOne(cascade = [CascadeType.ALL])
        val author: User,
        @Column(columnDefinition = "TEXT")
        val content: String
)