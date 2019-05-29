package pl.salon24.parser

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import pl.salon24.crawler.Site
import pl.salon24.model.entity.Tag
import pl.salon24.model.repository.TagRepository

@Component
class TagExtractor(private val tagRepository: TagRepository) {

    fun extractTags(site: Site): List<Tag> {
        val categories = site.document.getElementsByClass("category-breadcrumb").first()

        return categories.select("a")
                .map { it.attr("href") }
                .map { createTagFromUrl(it) }
                .map { handleExisting(it) }
    }

    fun createTagFromUrl(url: String): Tag {
        val matchResult = (Regex("//www.salon24.pl/k/(\\d+),(.+)").find(url)
                ?: throw RuntimeException("Unable to extract tag info from url: $url"))

        val id = matchResult.groupValues[1]
        val tag = matchResult.groupValues[2]

        return Tag(id, tag, mutableSetOf())
    }

    fun handleExisting(tag: Tag): Tag =
            when (val tagFromDb = tagRepository.findByIdOrNull(tag.id)) {
                null -> tag
                else -> tagFromDb
            }
}