package com.salon24.components;

import com.salon24.dto.GetCommentsDto;
import org.springframework.web.client.RestTemplate;

public class CommentsFetcher {
    private final String sourceId;
    private final String sort;
    private final Integer limit;
    private final Integer last;
    private final Integer selected;

    private CommentsFetcher(String sourceId, String sort, Integer limit, Integer last, Integer selected) {
        this.sourceId = sourceId;
        this.sort = sort;
        this.limit = limit;
        this.last = last;
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "https://www.salon24.pl/comments-api/comments?" +
                (sourceId != null ? "sourceId=" + sourceId : "") +
                (sort != null ? "&sort=" + sort : "") +
                (limit != null ? "&limit=" + limit : "") +
                (last != null ? "&last=" + last : "") +
                (selected != null ? "&selected=" + selected : "");
    }

    public static class Builder {
        private final RestTemplate restTemplate = new RestTemplate();

        private String sourceId;
        private String sort;
        private Integer limit;
        private Integer last;
        private Integer selected;

        public Builder sourceId(String sourceId) {
            this.sourceId = sourceId;
            return this;
        }

        public Builder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder last(Integer last) {
            this.last = last;
            return this;
        }

        public Builder selected(Integer selected) {
            this.selected = selected;
            return this;
        }

        public GetCommentsDto fetch() {
            String url = new CommentsFetcher(sourceId, sort, limit, last, selected).toString();
            return restTemplate.getForObject(url, GetCommentsDto.class);
        }
    }
}
