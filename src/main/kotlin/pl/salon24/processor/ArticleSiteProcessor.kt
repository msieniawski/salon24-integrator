package pl.salon24.processor

import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.model.entity.Article
import pl.salon24.model.repository.ArticleRepository
import pl.salon24.utils.logger
import java.lang.RuntimeException

@Component
class ArticleSiteProcessor(private val articleRepository: ArticleRepository) : SiteProcessor {
    private val log by logger()

    companion object {
        private val ID_IN_URL_REGEX = Regex("https://www.salon24.pl/u/.+/(\\d+),.+")
    }

    override fun process(site: Site) {
        log.debug("Processing ARTICLE: ${site.url}")

        val id = extractIdFromUrl(site.url)
        val title = site.document.select("h1").text()
        val content = site.document.getElementsByClass("article-content").text()

        val article = Article(id, title, content)
        articleRepository.save(article)
    }

    private fun extractIdFromUrl(url: String): String {
        val matchResult = ID_IN_URL_REGEX.find(url) ?: throw RuntimeException("Unable to extract article id from url: $url")
        return matchResult.groupValues.first()
    }
}