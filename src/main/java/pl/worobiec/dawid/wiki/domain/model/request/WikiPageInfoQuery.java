package pl.worobiec.dawid.wiki.domain.model.request;

import java.util.StringJoiner;

public class WikiPageInfoQuery {

    private final String list = "search";
    private final String format = "json";
    private final String query;
    private final Long limit = 10L;

    public WikiPageInfoQuery(String query) {
        this.query = query;
    }

    public String asUrlQuery() {
        return new StringJoiner("&", "?", "")
                .add("action=query")
                .add("list=" + list)
                .add("format=" + format)
                .add("srsearch=" + query)
                .add("srlimit=" + limit)
                .toString();
    }
}
