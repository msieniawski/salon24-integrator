package com.salon24.crawler

import org.jsoup.Jsoup
import org.springframework.stereotype.Component

/**
 * WERY NAIW IMPLEMENTEJSZON. SZUD BI DAN ON FREDS.
 */
@Component
class SiteCrawler(private val siteProcessor: SiteProcessor, private val urlExtractor: UrlExtractor) {
    private val processed = mutableSetOf<String>()

    fun crawl(url: String) {
        val document = Jsoup.connect(url).get()
        val urls = urlExtractor.extractUrlsFromSite(document)

        siteProcessor.process(SiteInfo(url, document, urls))
        processed.add(url)

        urls.all.filterNot { processed.contains(it) }
                .forEach { crawl(it) }
    }
}