package pl.worobiec.dawid.wiki.service.impl;

import pl.worobiec.dawid.wiki.client.WikiHttpClient;
import pl.worobiec.dawid.wiki.domain.exception.NoFootballClubFoundInResponseException;
import pl.worobiec.dawid.wiki.domain.model.request.WikiPageInfoQuery;
import pl.worobiec.dawid.wiki.domain.model.request.WikiPageUrlQuery;
import pl.worobiec.dawid.wiki.domain.model.response.FootballClubPage;
import pl.worobiec.dawid.wiki.domain.model.response.WikiPageInfo;
import pl.worobiec.dawid.wiki.domain.model.response.WikiPageUrlInfo;
import pl.worobiec.dawid.wiki.domain.model.response.WikiQueryResponse;
import pl.worobiec.dawid.wiki.service.FootballClubWikiService;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FootballClubWikiServiceImpl implements FootballClubWikiService {

    private final WikiHttpClient wikiHttpClient;

    private final Pattern footballClubSnippetRegex;

    public FootballClubWikiServiceImpl(WikiHttpClient wikiHttpClient, String footballClubRegex) {
        this.wikiHttpClient = wikiHttpClient;
        this.footballClubSnippetRegex = Pattern.compile(footballClubRegex, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Mono<FootballClubPage> getWikiFootballClub(WikiPageInfoQuery wikiPageInfoQuery) {
        return wikiHttpClient
                .queryWikiPages(wikiPageInfoQuery)
                .map(this::retrieveMatchingWikiFootballPage)
                .flatMap(this::parseAsFootballClubPage);
    }

    private WikiPageInfo retrieveMatchingWikiFootballPage(WikiQueryResponse<List<WikiPageInfo>, WikiPageInfo.QueryWrapper> wikiQueryResponse) {
        var wikiPagesInfo = wikiQueryResponse.getResponseData();

        return wikiPagesInfo.stream()
                .filter(this::isFootballClubPage)
                .findFirst()
                .orElseThrow(NoFootballClubFoundInResponseException::new);
    }

    private Mono<FootballClubPage> parseAsFootballClubPage(WikiPageInfo matchingWikiPageInfo) {
        var pageid = matchingWikiPageInfo.getPageId();
        var wikiPageUrlQuery = new WikiPageUrlQuery(pageid);
        return wikiHttpClient
                .queryWikiPageUrlDetails(wikiPageUrlQuery)
                .map(it -> getWikiFullUrl(it, pageid));

    }

    private FootballClubPage getWikiFullUrl(
            WikiQueryResponse<Map<String, WikiPageUrlInfo>, WikiPageUrlInfo.QueryWrapper> wikiPagesUrlDetails,
            Long pageid
    ) {
        var fullurl = wikiPagesUrlDetails
                .getResponseData()
                .get(pageid.toString())
                .getFullurl();
        return new FootballClubPage(fullurl);
    }

    private boolean isFootballClubPage(WikiPageInfo wikiPageInfo) {
        return footballClubSnippetRegex.matcher(wikiPageInfo.getSnippet()).find();
    }
}
