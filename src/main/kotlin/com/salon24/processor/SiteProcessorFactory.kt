package com.salon24.processor

import com.salon24.crawler.SiteType

object SiteProcessorFactory {

    fun fromType(siteType: SiteType): SiteProcessor =
            when (siteType) {
                SiteType.ARTICLE -> ArticleSiteProcessor
                SiteType.USER -> UserSiteProcessor
                SiteType.TAG -> TagSiteProcessor
                SiteType.OTHER -> OtherSiteProcessor
            }
}