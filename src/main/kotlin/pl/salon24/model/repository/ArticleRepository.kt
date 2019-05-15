package pl.salon24.model.repository

import pl.salon24.model.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, String>