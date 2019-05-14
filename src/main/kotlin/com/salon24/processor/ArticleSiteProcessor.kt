package com.salon24.processor

import com.salon24.crawler.SiteInfo
import com.salon24.utils.logger
import org.springframework.stereotype.Component

@Component
class ArticleSiteProcessor : SiteProcessor {
    private val log by logger()

    override fun process(site: SiteInfo) {
        log.debug("Processing an article: ${site.url}")
    }
}