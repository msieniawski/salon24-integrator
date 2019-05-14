package com.salon24.processor

import com.salon24.crawler.Site
import org.springframework.stereotype.Component

@Component
class OtherSiteProcessor : SiteProcessor {
    override fun process(site: Site) {
        // Do nothing
    }
}