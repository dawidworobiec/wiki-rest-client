package pl.worobiec.dawid.wiki.config.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wiki.service")
public class ServiceConfigurationProperties {

    private String footballClubRegex;

    public void setFootballClubRegex(String footballClubRegex) {
        this.footballClubRegex = footballClubRegex;
    }

    public String getFootballClubRegex() {
        return footballClubRegex;
    }
}
