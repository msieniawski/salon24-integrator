package com.salon24.crawler

import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL

@Component
class UrlExtractor(private val urlPartitioner: UrlPartitioner) {

    fun extractUrlsFromSite(document: Document) =
            urlPartitioner.partition(getAllUrls(document))

    private fun getAllUrls(document: Document): List<String> =
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