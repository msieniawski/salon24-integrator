package com.salon24.model.repository

import com.salon24.model.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, String>