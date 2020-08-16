package pl.worobiec.dawid.wiki.tests;

import org.junit.jupiter.api.Test;
import pl.worobiec.dawid.wiki.TestConfig;

import static pl.worobiec.dawid.wiki.stubs.WikiClientStubs.*;

public class WikiIntegrationTests extends TestConfig {

    @Test
    public void shouldReturnValidUrlWithOkStatusResult() {
        // given
        stubResponseWithValidFootballClubInResults();
        stubResponseWithValidFootballClubUrlInResults();

        // when
        webTestClient.get()
                .uri("/wiki/football-club/liverpool")
                .exchange()
                // then
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("https://en.wikipedia.org/wiki/Liverpool_F.C.");
    }

    @Test
    public void shouldReturnValidMessageAndNotFoundStatusIfFootballClubIsMissingInTop10() {
        // given
        stubResponseWithMissingFootballClubInResults();

        // when
        webTestClient.get()
                .uri("/wiki/football-club/lll")
                .exchange()
                // then
                .expectStatus().isNotFound()
                .expectBody(String.class).isEqualTo("No football club found in top 10 results");
    }

}
