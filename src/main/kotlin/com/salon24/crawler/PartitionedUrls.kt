package com.salon24.crawler

data class PartitionedUrls(
        val articles: MutableList<String> = mutableListOf(),
        val tags: MutableList<String> = mutableListOf(),
        val users: MutableList<String> = mutableListOf(),
        val others: MutableList<String> = mutableListOf()
)