package pl.worobiec.dawid.wiki.domain.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WikiPageInfo {

    private final Long pageId;
    private final String snippet;

    @JsonCreator
    public WikiPageInfo(
            @JsonProperty("pageid") Long pageId,
            @JsonProperty("snippet") String snippet) {
        this.pageId = pageId;
        this.snippet = snippet;
    }

    public Long getPageId() {
        return pageId;
    }

    public String getSnippet() {
        return snippet;
    }

    public static class QueryWrapper extends QueryWrapperAbstract<List<WikiPageInfo>> {

        private final List<WikiPageInfo> responseData;

        @JsonCreator
        public QueryWrapper(@JsonProperty("search") List<WikiPageInfo> responseData) {
            this.responseData = responseData;
        }

        @Override
        public List<WikiPageInfo> getResponseData() {
            return responseData;
        }
    }
}

