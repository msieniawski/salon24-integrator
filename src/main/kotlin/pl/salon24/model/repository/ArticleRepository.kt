package pl.salon24.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.salon24.model.entity.Article

interface ArticleRepository : JpaRepository<Article, String>