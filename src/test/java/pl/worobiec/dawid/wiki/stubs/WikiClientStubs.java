package pl.worobiec.dawid.wiki.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WikiClientStubs {

    public static void stubResponseWithValidFootballClubInResults() {
        stubFor(get(urlEqualTo("/w/api.php?action=query&list=search&format=json&srsearch=liverpool&srlimit=10"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json; charset=utf-8")
                                .withBodyFile("ResponseWithValidFootballClubInResults.json")
                ));
    }

    public static void stubResponseWithValidFootballClubUrlInResults() {
        stubFor(get(urlEqualTo("/w/api.php?action=query&prop=info&inprop=url&format=json&pageids=18119"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("ResponseWithValidFootballClubUrlInResults.json")
                ));
    }


    public static void stubResponseWithMissingFootballClubInResults() {
        stubFor(get(urlEqualTo("/w/api.php?action=query&list=search&format=json&srsearch=lll&srlimit=10"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json; charset=utf-8")
                                .withBodyFile("ResponseWithMissingFootballClubInResults.json")
                ));
    }
}
