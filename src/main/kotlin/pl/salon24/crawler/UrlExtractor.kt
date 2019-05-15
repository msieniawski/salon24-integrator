package pl.salon24.crawler

import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL

@Component
class UrlExtractor {

    fun extractSalon24UrlsFromSite(document: Document): List<String> =
            extractUrlsFromSite(document)
                    .filter { it.startsWith("https://www.salon24.pl") }
                    .filterNot { it.startsWith("https://www.salon24.pl/galeria") }

    private fun extractUrlsFromSite(document: Document): List<String> =
            document.select("a")
                    .map { it.attr("href") }
                    .map { fixProtocol(it) }
                    .filter { isUrl(it) }
                    .distinct()

    private fun fixProtocol(url: String) =
            if (url.startsWith("//")) {
                url.replace("//", "https://")
            } else {
                url
            }

    private fun isUrl(str: String) =
            try {
                URL(str)
                true
            } catch (e: MalformedURLException) {
                false
            }
}