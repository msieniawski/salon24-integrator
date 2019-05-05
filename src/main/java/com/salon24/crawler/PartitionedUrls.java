package com.salon24.crawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PartitionedUrls {
    private final List<String> articles = new ArrayList<>();
    private final List<String> tags = new ArrayList<>();
    private final List<String> users = new ArrayList<>();
    private final List<String> other = new ArrayList<>();

    List<String> getArticles() {
        return articles;
    }

    List<String> getTags() {
        return tags;
    }

    List<String> getUsers() {
        return users;
    }

    List<String> getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "PartitionedUrls{" +
                "articles=" + Arrays.toString(articles.toArray()) +
                "\ntags=" + Arrays.toString(tags.toArray()) +
                "\nusers=" + Arrays.toString(users.toArray()) +
                "\nother=" + Arrays.toString(other.toArray()) +
                '}';
    }
}
