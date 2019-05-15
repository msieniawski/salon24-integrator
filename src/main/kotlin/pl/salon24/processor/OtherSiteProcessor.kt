package pl.salon24.processor

import pl.salon24.crawler.Site
import org.springframework.stereotype.Component
import pl.salon24.utils.logger

@Component
class OtherSiteProcessor : SiteProcessor {
    private val log by logger()

    override fun process(site: Site) {
        log.debug("Processing OTHER: ${site.url}")
    }
}