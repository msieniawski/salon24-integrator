package pl.salon24.model.mapper

import org.springframework.stereotype.Component
import pl.salon24.comments.CommentDto
import pl.salon24.model.entity.Comment

@Component
class CommentDtoToEntityMapper : Mapper<CommentDto, Comment> {

    override fun map(from: CommentDto) =
            Comment(
                    id = from.id,
                    userId = from.userId,
                    created = from.created,
                    content = from.content,
                    format = from.format,
                    replies = from.replies,
                    likes = from.likes,
                    dislikes = from.dislikes,
                    votes = from.votes,
                    hidden = from.hidden,
                    deleted = from.deleted
                    //comments = map(from.comments?.data ?: emptyList())
            )
}