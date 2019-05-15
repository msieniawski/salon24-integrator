package pl.salon24.processor

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.model.repository.ArticleRepository
import pl.salon24.utils.logger

@Component
class ArticleSiteProcessor(private val articleRepository: ArticleRepository) : SiteProcessor {
    private val log by logger()

    override fun process(site: Site) {
        log.info("Processing an article: ${site.url}")
    }
}