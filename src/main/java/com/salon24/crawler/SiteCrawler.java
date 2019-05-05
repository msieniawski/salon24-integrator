package com.salon24.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class SiteCrawler {
    private final UrlExtractor urlExtractor;

    @Autowired
    SiteCrawler(UrlExtractor urlExtractor) {
        this.urlExtractor = urlExtractor;
    }

    void crawl(String siteUrl) throws IOException {
        Document document = Jsoup.connect(siteUrl).get();

        PartitionedUrls urls = urlExtractor.getUrlsFromSite(document);
        System.out.println(urls);

        // TODO: Do sth with urls and site
    }
}
