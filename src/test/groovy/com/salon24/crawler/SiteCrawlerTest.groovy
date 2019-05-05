package com.salon24.crawler

import spock.lang.Specification

class SiteCrawlerTest extends Specification {

    def "Should return correct urls"() {
        given:
        SiteCrawler siteCrawler = new SiteCrawler()

        when:
        def urls = siteCrawler.getAllUrlsOnSite(
                "https://www.salon24.pl/u/bognajanke/951319,augustowskie-patologie-lokalna-grupa-trzymajaca-wladze")

        then:
        urls.forEach { println it }
        true
    }
}
