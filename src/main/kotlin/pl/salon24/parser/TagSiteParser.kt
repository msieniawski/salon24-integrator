package pl.salon24.parser

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.persister.TagPersister
import pl.salon24.utils.logger

@Component
class TagSiteParser(
        private val tagPersister: TagPersister,
        private val tagExtractor: TagExtractor
) : SiteParser {
    private val log by logger()

    override fun parse(site: Site) {
        log.debug("Parsing TAG: ${site.url}")

        val tag = tagExtractor.createTagFromUrl(site.url)
                .let { tagExtractor.handleExisting(it) }

        tagPersister.persist(tag)
    }
}
