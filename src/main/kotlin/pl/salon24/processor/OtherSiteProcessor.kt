package pl.salon24.processor

import pl.salon24.crawler.Site
import org.springframework.stereotype.Component

@Component
class OtherSiteProcessor : SiteProcessor {
    override fun process(site: Site) {
        // Do nothing
    }
}