package com.salon24.crawler;

import com.salon24.crawler.SiteCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class SiteCrawlerRunner implements ApplicationRunner {
    private final SiteCrawler crawler;

    @Value("${initial.url}")
    private String initialUrl;

    @Autowired
    SiteCrawlerRunner(SiteCrawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public void run(ApplicationArguments args) {
        crawler.crawl(initialUrl);
    }
}
