package com.salon24.crawler

import spock.lang.Specification

class UrlPartitionerTest extends Specification {

    def "Should partition urls"() {
        given:
        def urls = new SiteCrawler().getAllUrlsOnSite(
                "https://www.salon24.pl/u/bognajanke/951319,augustowskie-patologie-lokalna-grupa-trzymajaca-wladze")
        UrlPartitioner urlPartitioner = new UrlPartitioner()

        when:
        PartitionedUrls paritionedUrls = urlPartitioner.paritionUrls(urls)

        then:
        urls.forEach { println it }
        println paritionedUrls
        true
    }
}
