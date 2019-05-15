package pl.salon24.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import pl.salon24.model.entity.ProcessedSite
import pl.salon24.model.repository.ProcessedSiteRepository
import pl.salon24.processor.SiteProcessorFactory
import pl.salon24.utils.logger

/**
 * WERY NAIW IMPLEMENTEJSZON. SZUD BI DAN ON FREDS.
 */
@Component
class SiteCrawler(
        private val urlExtractor: UrlExtractor,
        private val siteClasifier: SiteClasifier,
        private val siteProcessorFactory: SiteProcessorFactory,
        private val processedSiteRepository: ProcessedSiteRepository
) {
    private val log by logger()

    fun crawl(url: String) {
        val document: Document = try {
            Jsoup.connect(url).get()
        } catch (e: Exception) {
            log.warn("Unable to download site: $url")
            return
        }

        log.info("Visiting $url")

        val site = Site(url = url,
                type = siteClasifier.getSiteTypeByUrl(url),
                document = document)

        processSite(site)
        processNext(site)
    }

    private fun processSite(site: Site) {
        val siteProcessor = siteProcessorFactory.fromType(site.type)
        siteProcessor.process(site)

        processedSiteRepository.save(ProcessedSite(site.url))
    }

    private fun processNext(site: Site) {
        urlExtractor.extractSalon24UrlsFromSite(site.document)
                .filterNot { processedSiteRepository.existsByUrl(it) }
                .forEach { crawl(it) }
    }
}