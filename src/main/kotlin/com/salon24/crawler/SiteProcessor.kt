package com.salon24.crawler

import org.springframework.stereotype.Component

@Component
class SiteProcessor {

    fun process(site: SiteInfo) {
        println("Processing: ${site.url}")
    }
}