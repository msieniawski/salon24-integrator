package com.salon24.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

class SiteCrawler {

    List<String> getAllUrlsOnSite(String siteUrl) throws IOException {
        Document doc = Jsoup.connect(siteUrl).get();
        Elements hrefs = doc.select("a");

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
