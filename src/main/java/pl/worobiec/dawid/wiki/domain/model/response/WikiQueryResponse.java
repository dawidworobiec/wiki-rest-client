package pl.worobiec.dawid.wiki.domain.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WikiQueryResponse<R, T extends QueryWrapperAbstract<R>> {

    private final T queryWrapper;

    @JsonCreator
    public WikiQueryResponse(@JsonProperty("query") T queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    public T getQueryWrapper() {
        return queryWrapper;
    }

    public R getResponseData() {
        return queryWrapper.getResponseData();
    }
}

abstract class QueryWrapperAbstract<R> {
    public abstract R getResponseData();
}

