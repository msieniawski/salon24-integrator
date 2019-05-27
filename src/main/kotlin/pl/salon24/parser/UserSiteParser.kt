package pl.salon24.parser

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.persister.UserPersister
import pl.salon24.utils.logger

@Component
class UserSiteParser(
        private val userExtractor: UserExtractor,
        private val userPersister: UserPersister
) : SiteParser {
    private val log by logger()

    override fun parse(site: Site) {
        log.debug("Parsing USER: ${site.url}")

        val user = userExtractor.extractUser(site)
        userPersister.persist(user)
    }
}
