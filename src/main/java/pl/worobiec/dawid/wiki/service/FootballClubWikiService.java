package pl.worobiec.dawid.wiki.service;

import pl.worobiec.dawid.wiki.domain.model.response.FootballClubPage;
import pl.worobiec.dawid.wiki.domain.model.request.WikiPageInfoQuery;
import reactor.core.publisher.Mono;

public interface FootballClubWikiService {

    Mono<FootballClubPage> getWikiFootballClub(WikiPageInfoQuery wikiPageInfoQuery);
}
