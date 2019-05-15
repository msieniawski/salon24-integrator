package pl.salon24.processor

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.model.repository.ArticleRepository
import pl.salon24.utils.logger

@Component
class ArticleSiteProcessor(private val articleRepository: ArticleRepository) : SiteProcessor {
    private val log by logger()

    override fun process(site: Site) {
        log.debug("Processing ARTICLE: ${site.url}")

        val title = site.document.select("h1").text()
        log.debug("title : $title")
    }
}