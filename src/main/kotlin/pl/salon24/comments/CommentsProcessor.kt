package pl.salon24.comments

import org.springframework.stereotype.Component
import pl.salon24.model.entity.Article
import pl.salon24.model.entity.Comment
import pl.salon24.model.mapper.CommentDtoToEntityMapper
import pl.salon24.model.repository.CommentRepository
import pl.salon24.utils.logger

@Component
class CommentsProcessor(
        private val commentMapper: CommentDtoToEntityMapper,
        private val commentRepository: CommentRepository
) {
    private val log by logger()

    fun processCommentsForArticle(article: Article) {
        log.debug("Fetching comments for article: ${article.title}")

        val getCommentsDto = CommentsFetcher("Post-${article.id}", "NEWEST", 20).fetch()

        if (getCommentsDto.error != null) {
            log.error("Error fetching comments for article: ${article.title}. Details: ${getCommentsDto.errorDesc}")
            return
        }

        val commentsDto: List<CommentDto> = getCommentsDto.data.comments.data
        val usersDtoById: Map<String, UserDto> = getCommentsDto.data.users

        val comments: List<Comment> = commentMapper.map(commentsDto)
        commentRepository.saveAll(comments)
    }
}