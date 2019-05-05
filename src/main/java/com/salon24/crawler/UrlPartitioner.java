package com.salon24.crawler;

import java.util.List;

class UrlPartitioner {

    PartitionedUrls paritionUrls(List<String> urls) {
        PartitionedUrls partitionedUrls = new PartitionedUrls();

        for (String url : urls) {
            if (isArticle(url)) {
                partitionedUrls.getArticles().add(url);
            } else if (isTag(url)) {
                partitionedUrls.getTags().add(url);
            } else if (isUser(url)) {
                partitionedUrls.getUsers().add(url);
            } else {
                partitionedUrls.getOther().add(url);
            }
        }

        return partitionedUrls;
    }

    private boolean isArticle(String url) {
       return url.matches("https://www.salon24.pl/u/(.+)/(\\d+),(.+)");
    }

    private boolean isTag(String url) {
        return url.matches("https://www.salon24.pl/k/(\\d+),(.+)");
    }

    private boolean isUser(String url) {
        return url.matches("https://www.salon24.pl/u/(.+)/");
    }
}
