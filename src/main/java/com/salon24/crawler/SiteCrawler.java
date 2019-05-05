package com.salon24.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
class SiteCrawler {
    private final SiteProcessor siteProcessor;
    private final UrlExtractor urlExtractor;

    private final Set<String> visited = new HashSet<>();

    @Autowired
    SiteCrawler(SiteProcessor siteProcessor, UrlExtractor urlExtractor) {
        this.siteProcessor = siteProcessor;
        this.urlExtractor = urlExtractor;
    }

    void crawl(String siteUrl) {
        Document document = downloadSite(siteUrl);
        PartitionedUrls urls = urlExtractor.getUrlsFromSite(document);

        siteProcessor.process(new SiteInfo(siteUrl, document, urls));
        visited.add(siteUrl);

        urls.getAll().stream()
                .filter(url -> !visited.contains(url))
                .forEach(this::crawl);
    }

    private Document downloadSite(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
