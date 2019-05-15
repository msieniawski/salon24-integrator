package pl.salon24.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.task.TaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.ProcessedSite
import pl.salon24.model.repository.ProcessedSiteRepository
import pl.salon24.processor.SiteProcessorFactory
import pl.salon24.utils.logger

@Component
class SiteCrawler(
        private val urlExtractor: UrlExtractor,
        private val siteClasifier: SiteClasifier,
        private val siteProcessorFactory: SiteProcessorFactory,
        private val processedSiteRepository: ProcessedSiteRepository,
        private val processSiteTaskExecutor: TaskExecutor
) : ApplicationRunner {
    private val log by logger()

    @Value("\${initial.url}")
    private lateinit var initialUrl: String

    override fun run(args: ApplicationArguments) {
        processSiteTaskExecutor.execute(ProcessSiteTask(initialUrl))
    }

    inner class ProcessSiteTask(private val url: String) : Runnable {
        override fun run() {
            val document: Document = downloadSite(url) ?: return

            log.info("Visiting $url")

            val site = Site(url = url,
                    type = siteClasifier.getSiteTypeByUrl(url),
                    document = document)

            processSite(site)
            processNext(site)
        }

        private fun downloadSite(url: String): Document? =
                try {
                    Jsoup.connect(url).get()
                } catch (e: Exception) {
                    log.warn("Unable to download site: $url")
                    null
                }

        private fun processSite(site: Site) {
            val siteProcessor = siteProcessorFactory.fromType(site.type)
            siteProcessor.process(site)

            processedSiteRepository.save(ProcessedSite(site.url))
        }

        private fun processNext(site: Site) {
            urlExtractor.extractSalon24UrlsFromSite(site.document)
                    .filterNot { processedSiteRepository.existsByUrl(it) }
                    .forEach { processSiteTaskExecutor.execute(ProcessSiteTask(it)) }
        }
    }

}