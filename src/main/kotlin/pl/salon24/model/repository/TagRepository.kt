package pl.salon24.model.repository

import pl.salon24.model.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, String>