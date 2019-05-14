package com.salon24.crawler

import com.salon24.model.Article
import org.springframework.stereotype.Component

@Component
class SiteProcessor {

    fun process(site: SiteInfo) {
        println("Processing: ${site.url} ${site.type}")

        val article = extractArticle(site)
    }

    private fun extractArticle(site: SiteInfo): Article {
        return Article("id", "title", "content")
    }
}