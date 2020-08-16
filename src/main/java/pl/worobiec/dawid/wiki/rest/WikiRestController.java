package pl.worobiec.dawid.wiki.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.worobiec.dawid.wiki.domain.model.response.FootballClubPage;
import pl.worobiec.dawid.wiki.domain.model.request.WikiPageInfoQuery;
import pl.worobiec.dawid.wiki.service.FootballClubWikiService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wiki")
public class WikiRestController {

    private final FootballClubWikiService footballClubWikiService;

    public WikiRestController(FootballClubWikiService footballClubWikiService) {
        this.footballClubWikiService = footballClubWikiService;
    }

    @GetMapping("/football-club/{name}")
    public ResponseEntity<Mono<String>> getWikiFootballClubs(@PathVariable String name) {
        WikiPageInfoQuery wikiPageInfoQuery = new WikiPageInfoQuery(name);
        Mono<FootballClubPage> wikiFootballClub = footballClubWikiService.getWikiFootballClub(wikiPageInfoQuery);
        Mono<String> footballClubPageUrl = wikiFootballClub.map(FootballClubPage::getFootballClubPageUrl);
        return ResponseEntity.ok(footballClubPageUrl);
    }
}
