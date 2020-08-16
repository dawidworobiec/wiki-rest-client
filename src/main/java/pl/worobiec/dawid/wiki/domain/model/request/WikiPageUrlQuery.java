package pl.worobiec.dawid.wiki.domain.model.request;

import java.util.StringJoiner;

public class WikiPageUrlQuery {

    private final Long pageid;

    public WikiPageUrlQuery(Long pageid) {
        this.pageid = pageid;
    }

    public String asUrlQuery() {
        return new StringJoiner("&", "?", "")
                .add("action=query")
                .add("prop=info")
                .add("inprop=url")
                .add("format=json")
                .add("pageids=" + pageid)
                .toString();
    }
}
