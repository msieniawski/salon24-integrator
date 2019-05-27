package pl.salon24.parser

import org.springframework.stereotype.Component
import pl.salon24.comments.CommentsProcessor
import pl.salon24.crawler.Site
import pl.salon24.model.entity.Article
import pl.salon24.persister.ArticlePersister
import pl.salon24.persister.TagPersister
import pl.salon24.utils.logger

@Component
class ArticleSiteParser(
        private val articlePersister: ArticlePersister,
        private val commentsProcessor: CommentsProcessor,
        private val userExtractor: UserExtractor,
        private val tagExtractor: TagExtractor,
        private val tagPersister: TagPersister
) : SiteParser {
    private val log by logger()

    companion object {
        private val ID_IN_URL_REGEX = Regex("https://www.salon24.pl/u/.+/(\\d+),.+")
    }

    override fun parse(site: Site) {
        log.debug("Parsing ARTICLE: ${site.url}")

        val id = extractIdFromUrl(site.url)
        val title = site.document.select("h1").text()
        val content = site.document.getElementsByClass("article-content").text()
        val author = userExtractor.extractUser(site)

        val tags = tagExtractor.extractTags(site).toSet()
        val article = Article(id, site.url, title, author, content, tags)
        tags.forEach { it.articles.add(article) }
        articlePersister.persist(article)

        //tagPersister.persist(tags)

        commentsProcessor.processCommentsForArticle(article)
    }

    private fun extractIdFromUrl(url: String): String {
        val matchResult = ID_IN_URL_REGEX.find(url)
                ?: throw RuntimeException("Unable to extract article id from url: $url")
        return matchResult.groupValues[1]
    }

}