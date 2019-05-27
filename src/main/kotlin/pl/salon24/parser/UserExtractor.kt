package pl.salon24.parser

import org.jsoup.nodes.Element
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.model.entity.User
import pl.salon24.model.repository.UserRepository

@Component
class UserExtractor(private val userRepository: UserRepository) {

    fun extractUser(site: Site): User {
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