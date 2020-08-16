package pl.worobiec.dawid.wiki.domain.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WikiPageUrlInfo {

    private final String fullurl;

    @JsonCreator
    public WikiPageUrlInfo(@JsonProperty("fullurl") String fullurl) {
        this.fullurl = fullurl;
    }

    public String getFullurl() {
        return fullurl;
    }

    public static class QueryWrapper extends QueryWrapperAbstract<Map<String, WikiPageUrlInfo>> {

        private final Map<String, WikiPageUrlInfo> responseData;

        @JsonCreator
        public QueryWrapper(@JsonProperty("pages") Map<String, WikiPageUrlInfo> responseData) {
            this.responseData = responseData;
        }

        @Override
        public Map<String, WikiPageUrlInfo> getResponseData() {
            return responseData;
        }
    }
}