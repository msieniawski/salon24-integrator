package pl.salon24.parser

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.utils.logger

@Component
class TagSiteParser : SiteParser {
    private val log by logger()

    override fun parse(site: Site) {
        log.debug("Parsing TAG: ${site.url}")
    }
}
