package pl.salon24.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import pl.salon24.model.entity.Tag

@RepositoryRestResource(collectionResourceRel = "TAGS", path = "tags")
interface TagRepository : JpaRepository<Tag, String>