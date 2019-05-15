package pl.salon24.crawler

import pl.salon24.processor.SiteProcessorFactory
import org.jsoup.Jsoup
import org.springframework.stereotype.Component

/**
 * WERY NAIW IMPLEMENTEJSZON. SZUD BI DAN ON FREDS.
 */
@Component
class SiteCrawler(
        private val urlExtractor: UrlExtractor,
        private val siteClasifier: SiteClasifier,
        private val siteProcessorFactory: SiteProcessorFactory
) {
    private val processed = mutableSetOf<String>()

    fun crawl(url: String) {
        val site = extractSiteInfo(url)

        val siteProcessor = siteProcessorFactory.fromType(site.type)
        siteProcessor.process(site)

        processed.add(url)

        urlExtractor.extractSalon24UrlsFromSite(site.document)
                .filterNot { processed.contains(it) }
                .forEach { crawl(it) }
    }

    private fun extractSiteInfo(url: String) =
            Site(
                    url = url,
                    type = siteClasifier.getSiteTypeByUrl(url),
                    document = Jsoup.connect(url).get()
            )
}