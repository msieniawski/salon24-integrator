package pl.salon24.userpersister

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.Article
import pl.salon24.model.repository.ArticleRepository

@Component
class ArticlePersister(
        articleRepository: ArticleRepository,
        persisterTaskExecutor: ThreadPoolTaskExecutor
) : Persister<Article, String>(articleRepository, persisterTaskExecutor)