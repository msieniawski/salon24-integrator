package pl.salon24.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import pl.salon24.model.entity.Comment

@RepositoryRestResource(collectionResourceRel = "COMMENTS", path = "comments")
interface CommentRepository : JpaRepository<Comment, String>