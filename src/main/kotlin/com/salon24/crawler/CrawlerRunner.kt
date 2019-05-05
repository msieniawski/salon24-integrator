package com.salon24.crawler

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class CrawlerRunner(private val crawler: SiteCrawler) : ApplicationRunner {

    @Value("\${initial.url}")
    private lateinit var initialUrl: String

    override fun run(args: ApplicationArguments) {
        crawler.crawl(initialUrl)
    }
}