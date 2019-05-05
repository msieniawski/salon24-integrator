package com.salon24.crawler;

import org.springframework.stereotype.Component;

@Component
class SiteProcessor {

    void process(SiteInfo site) {
        System.out.println("Visiting: " + site.getUrl());
    }
}
