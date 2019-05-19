package pl.salon24.model.mapper

import org.springframework.stereotype.Component
import pl.salon24.comments.CommentDto
import pl.salon24.model.entity.Comment
import pl.salon24.model.entity.User
import java.lang.RuntimeException

@Component
class CommentDtoToEntityMapper {

    fun map(from: Iterable<CommentDto>, usersById: Map<String, User>): List<Comment> =
            from.map { map(it, usersById) }


    private fun map(from: CommentDto, usersById: Map<String, User>) =
            Comment(
                    id = from.id,
                    author = usersById[from.userId] ?: throw RuntimeException("Invalid response (user not found with id: ${from.userId})"),
                    created = from.created,
                    content = from.content,
                    format = from.format,
                    replies = from.replies,
                    likes = from.likes,
                    dislikes = from.dislikes,
                    votes = from.votes,
                    hidden = from.hidden,
                    deleted = from.deleted,
                    comments = map(from.comments?.data ?: emptyList(), usersById).toSet()
            )
}