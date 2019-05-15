package pl.salon24.processor

import pl.salon24.crawler.Site

interface SiteProcessor {
    fun process(site: Site)
}

