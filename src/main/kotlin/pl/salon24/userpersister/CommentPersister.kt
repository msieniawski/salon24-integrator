package pl.salon24.userpersister

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.Comment
import pl.salon24.model.repository.CommentRepository

@Component
class CommentPersister(
        commentRepository: CommentRepository,
        persisterTaskExecutor: ThreadPoolTaskExecutor
) : Persister<Comment, String>(commentRepository, persisterTaskExecutor)