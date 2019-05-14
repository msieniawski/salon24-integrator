package com.salon24.processor

import com.salon24.crawler.SiteInfo

interface SiteProcessor {
    fun process(site: SiteInfo)
}

