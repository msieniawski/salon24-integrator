package pl.salon24.processor

import pl.salon24.crawler.Site
import pl.salon24.utils.logger
import org.springframework.stereotype.Component

@Component
class ArticleSiteProcessor : SiteProcessor {
    private val log by logger()

    override fun process(site: Site) {
        log.debug("Processing an article: ${site.url}")
    }
}