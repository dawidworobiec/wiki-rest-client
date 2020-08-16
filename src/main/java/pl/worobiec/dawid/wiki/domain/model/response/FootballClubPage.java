package pl.worobiec.dawid.wiki.domain.model.response;

public class FootballClubPage {

    private final String footballClubPageUrl;

    public FootballClubPage(String footballClubPageUrl) {
        this.footballClubPageUrl = footballClubPageUrl;
    }

    public String getFootballClubPageUrl() {
        return footballClubPageUrl;
    }
}
