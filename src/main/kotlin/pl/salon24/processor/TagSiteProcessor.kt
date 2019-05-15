package pl.salon24.processor

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.utils.logger

@Component
class TagSiteProcessor : SiteProcessor {
    private val log by logger()

    override fun process(site: Site) {
        log.debug("Processing TAG: ${site.url}")
    }
}
