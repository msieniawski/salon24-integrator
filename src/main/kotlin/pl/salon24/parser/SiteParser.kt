package pl.salon24.parser

import pl.salon24.crawler.Site

interface SiteParser {
    fun parse(site: Site)
}

