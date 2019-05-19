package pl.salon24.comments

import org.springframework.stereotype.Component
import pl.salon24.model.entity.Article
import pl.salon24.model.entity.Comment
import pl.salon24.model.mapper.CommentDtoToEntityMapper
import pl.salon24.model.mapper.UserDtoToEntityMapper
import pl.salon24.persister.CommentPersister
import pl.salon24.persister.UserPersister
import pl.salon24.utils.logger

@Component
class CommentsProcessor(
        private val commentMapper: CommentDtoToEntityMapper,
        private val commentPersister: CommentPersister,
        private val userMapper: UserDtoToEntityMapper,
        private val userPersister: UserPersister
) {
    private val log by logger()

    fun processCommentsForArticle(article: Article) {
        log.debug("Fetching comments for article: ${article.title}")

        val getCommentsDto = CommentsFetcher("Post-${article.id}", "NEWEST", 20).fetch()

        if (getCommentsDto.error != null) {
            log.error("Error fetching comments for article: ${article.title}. Details: ${getCommentsDto.errorDesc}")
            return
        }

        val usersDto: MutableCollection<UserDto> = getCommentsDto.data.users.values
        val commentsDto: List<CommentDto> = getCommentsDto.data.comments.data

        val users = userMapper.map(usersDto)
        userPersister.persist(users)

        val comments: List<Comment> = commentMapper.map(commentsDto, users)
        commentPersister.persist(comments)
    }
}