package pl.worobiec.dawid.wiki.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import pl.worobiec.dawid.wiki.config.WikiHttpClientProperties;
import pl.worobiec.dawid.wiki.domain.exception.WikiClientErrorException;
import pl.worobiec.dawid.wiki.domain.exception.WikiServerErrorException;
import pl.worobiec.dawid.wiki.domain.model.request.WikiPageInfoQuery;
import pl.worobiec.dawid.wiki.domain.model.request.WikiPageUrlQuery;
import pl.worobiec.dawid.wiki.domain.model.response.WikiPageInfo;
import pl.worobiec.dawid.wiki.domain.model.response.WikiPageUrlInfo;
import pl.worobiec.dawid.wiki.domain.model.response.WikiQueryResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class WikiHttpClient {

    private final WebClient wikiWebClient;
    private final WikiHttpClientProperties wikiHttpClientProperties;

    public WikiHttpClient(WebClient wikiWebClient, WikiHttpClientProperties wikiHttpClientProperties) {
        this.wikiWebClient = wikiWebClient;
        this.wikiHttpClientProperties = wikiHttpClientProperties;
    }

    public Mono<WikiQueryResponse<List<WikiPageInfo>, WikiPageInfo.QueryWrapper>> queryWikiPages(
            WikiPageInfoQuery wikiPageInfoQuery
    ) {
        return wikiWebClient.get()
                .uri(buildUrl(wikiPageInfoQuery))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new WikiClientErrorException(clientResponse)))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new WikiServerErrorException(clientResponse)))
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<WikiQueryResponse<Map<String, WikiPageUrlInfo>, WikiPageUrlInfo.QueryWrapper>> queryWikiPageUrlDetails(
            WikiPageUrlQuery wikiPageUrlQuery
    ) {
        return wikiWebClient.get()
                .uri(buildUrl(wikiPageUrlQuery))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new WikiClientErrorException(clientResponse)))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new WikiClientErrorException(clientResponse)))
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    private String buildUrl(WikiPageUrlQuery wikiPageUrlQuery) {
        return wikiHttpClientProperties.getEndpoint() + wikiPageUrlQuery.asUrlQuery();
    }

    private String buildUrl(WikiPageInfoQuery wikiPageInfoQuery) {
        return wikiHttpClientProperties.getEndpoint() + wikiPageInfoQuery.asUrlQuery();
    }
}
