package pl.salon24.processor

import pl.salon24.crawler.SiteType
import org.springframework.stereotype.Component

@Component
class SiteProcessorFactory(
        private val articleSiteProcessor: ArticleSiteProcessor,
        private val userSiteProcessor: UserSiteProcessor,
        private val tagSiteProcessor: TagSiteProcessor,
        private val otherSiteProcessor: OtherSiteProcessor
) {

    fun fromType(siteType: SiteType): SiteProcessor =
            when (siteType) {
                SiteType.ARTICLE -> articleSiteProcessor
                SiteType.USER -> userSiteProcessor
                SiteType.TAG -> tagSiteProcessor
                SiteType.OTHER -> otherSiteProcessor
            }
}