package com.salon24.model.repository

import com.salon24.model.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, String>