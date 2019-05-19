package pl.salon24.parser

import org.springframework.stereotype.Component
import pl.salon24.crawler.SiteType

@Component
class SiteParserFactory(
        private val articleSiteParser: ArticleSiteParser,
        private val userSiteParser: UserSiteParser,
        private val tagSiteParser: TagSiteParser,
        private val otherSiteParser: OtherSiteParser
) {

    fun fromType(siteType: SiteType): SiteParser =
            when (siteType) {
                SiteType.ARTICLE -> articleSiteParser
                SiteType.USER -> userSiteParser
                SiteType.TAG -> tagSiteParser
                SiteType.OTHER -> otherSiteParser
            }
}