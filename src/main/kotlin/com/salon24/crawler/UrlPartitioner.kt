package com.salon24.crawler

import org.springframework.stereotype.Component

@Component
class UrlPartitioner {
    companion object {
        private val IS_ARTICLE_REGEX = Regex("https://www.salon24.pl/u/(.+)/(\\d+),(.+)")
        private val IS_TAG_REGEX = Regex("https://www.salon24.pl/k/(\\d+),(.+)")
        private val IS_USER_REGEX = Regex("https://www.salon24.pl/u/(.+)/")
    }

    fun partition(urls: List<String>): PartitionedUrls {
        val partitionedUrls = PartitionedUrls()

        urls.forEach { url ->
            when {
                url.matches(IS_ARTICLE_REGEX) -> partitionedUrls.articles.add(url)
                url.matches(IS_TAG_REGEX) -> partitionedUrls.tags.add(url)
                url.matches(IS_USER_REGEX) -> partitionedUrls.users.add(url)
                else -> partitionedUrls.others.add(url)
            }
        }

        return partitionedUrls
    }

}