package com.salon24.processor

import com.salon24.crawler.Site

interface SiteProcessor {
    fun process(site: Site)
}

