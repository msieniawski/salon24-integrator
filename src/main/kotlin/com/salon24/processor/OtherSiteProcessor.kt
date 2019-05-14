package com.salon24.processor

import com.salon24.crawler.SiteInfo
import org.springframework.stereotype.Component

@Component
class OtherSiteProcessor : SiteProcessor {
    override fun process(site: SiteInfo) {
        // Do nothing
    }
}