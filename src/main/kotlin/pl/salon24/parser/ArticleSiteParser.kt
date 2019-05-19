package pl.salon24.parser

import org.jsoup.nodes.Element
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import pl.salon24.comments.CommentsProcessor
import pl.salon24.crawler.Site
import pl.salon24.model.entity.Article
import pl.salon24.model.entity.User
import pl.salon24.model.repository.UserRepository
import pl.salon24.persister.ArticlePersister
import pl.salon24.utils.logger

@Component
class ArticleSiteParser(
        private val articlePersister: ArticlePersister,
        private val commentsProcessor: CommentsProcessor,
        private val userRepository: UserRepository
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

        val article = Article(id, site.url, title, content)
        articlePersister.persist(article)

        extractUser(site)

        commentsProcessor.processCommentsForArticle(article)
    }

    private fun extractIdFromUrl(url: String): String {
        val matchResult = ID_IN_URL_REGEX.find(url) ?: throw RuntimeException("Unable to extract article id from url: $url")
        return matchResult.groupValues[1]
    }

    private fun extractUser(site: Site): User {
        val followButton: Element = site.document.select("button[data-follow^=User:]").first()
        val userId = followButton.attr("data-follow").split(":")[1]

        return when (val user = userRepository.findByIdOrNull(userId)) {
            null -> {
                val userUrlElement = site.document.getElementsByClass("user-header__user-nick").first().child(0)
                val name = userUrlElement.text()
                val url = userUrlElement.attr("href")

                val userImageElement = site.document.select("img[alt=$name]")
                val imageUrl = userImageElement.attr("src")

                User(userId, url, name, imageUrl)
            }
            else -> user
        }
    }

}