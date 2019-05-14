package com.salon24.crawler

import spock.lang.Specification

class SiteCrawlerTest extends Specification {

    def "Should crawl site"() {
        given:
        SiteCrawler siteCrawler = new SiteCrawler(new UrlExtractor(new SiteClasifier()))

        when:
        siteCrawler.crawl("https://www.salon24.pl/u/bognajanke/951319,augustowskie-patologie-lokalna-grupa-trzymajaca-wladze")

        then:
        true
    }
}
