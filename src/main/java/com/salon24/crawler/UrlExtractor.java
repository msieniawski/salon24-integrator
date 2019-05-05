package com.salon24.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
class UrlExtractor {
    private final UrlPartitioner urlPartitioner;

    @Autowired
    UrlExtractor(UrlPartitioner urlPartitioner) {
        this.urlPartitioner = urlPartitioner;
    }

    PartitionedUrls getUrlsFromSite(Document document) {
        return urlPartitioner.paritionUrls(getAllUrls(document));
    }

    private List<String> getAllUrls(Document document) {
        Elements hrefs = document.select("a");

        return hrefs.stream()
                .map(element -> element.attr("href"))
                .map(this::sanitize)
                .filter(this::isUrl)
                .distinct()
                .collect(Collectors.toList());
    }

    private String sanitize(String url) {
        return url.startsWith("//") ? url.replace("//", "https://") : url;
    }

    private boolean isUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
