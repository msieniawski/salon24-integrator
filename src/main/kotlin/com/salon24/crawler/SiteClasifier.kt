package com.salon24.crawler

import org.springframework.stereotype.Component

@Component
class SiteClasifier {
    companion object {
        private val IS_ARTICLE_REGEX = Regex("https://www.salon24.pl/u/(.+)/(\\d+),(.+)")
        private val IS_TAG_REGEX = Regex("https://www.salon24.pl/k/(\\d+),(.+)")
        private val IS_USER_REGEX = Regex("https://www.salon24.pl/u/(.+)/")
    }

    fun getSiteTypeByUrl(url: String): SiteType =
            when {
                url.matches(IS_ARTICLE_REGEX) -> SiteType.ARTICLE
                url.matches(IS_TAG_REGEX) -> SiteType.TAG
                url.matches(IS_USER_REGEX) -> SiteType.USER
                else -> SiteType.OTHER
            }

}