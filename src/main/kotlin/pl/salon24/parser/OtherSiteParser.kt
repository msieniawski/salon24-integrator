package pl.salon24.parser

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.utils.logger

@Component
class OtherSiteParser : SiteParser {
    private val log by logger()

    override fun parse(site: Site) {
        log.debug("Parsing OTHER: ${site.url}")
    }
}