package com.salon24.comments

import org.springframework.web.client.RestTemplate

class CommentsFetcher(
        sourceId: String? = null,
        sort: String? = null,
        limit: Int? = null,
        last: Int? = null,
        selected: Int? = null
) {
    private val url = "https://www.salon24.pl/comments-api/comments?" +
            (sourceId?.let { "sourceId=$it" } ?: "") +
            (sort?.let { "&sort=$it" } ?: "") +
            (limit?.let { "&limit=$it" } ?: "") +
            (last?.let { "&last=$it" } ?: "") +
            (selected?.let { "&selected=$it" } ?: "")

    private val restTemplate = RestTemplate()

    fun fetch(): GetCommentsDto = restTemplate.getForObject(url, GetCommentsDto::class.java)
            ?: throw RuntimeException("Unable to fetch comments: $url")
}

