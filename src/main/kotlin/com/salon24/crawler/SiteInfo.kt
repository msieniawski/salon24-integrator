package com.salon24.crawler

import org.jsoup.nodes.Document

data class SiteInfo(
        val url: String,
        val type: SiteType,
        val document: Document
)