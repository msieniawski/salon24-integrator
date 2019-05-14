package com.salon24.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ARTICLES")
data class Article(
        @Id
        val id: String,
        val title: String,
        val content: String
)