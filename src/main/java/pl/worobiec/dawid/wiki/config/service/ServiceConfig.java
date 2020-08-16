package pl.worobiec.dawid.wiki.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.worobiec.dawid.wiki.client.WikiHttpClient;
import pl.worobiec.dawid.wiki.service.FootballClubWikiService;
import pl.worobiec.dawid.wiki.service.impl.FootballClubWikiServiceImpl;

@Configuration
@EnableConfigurationProperties(ServiceConfigurationProperties.class)
public class ServiceConfig {

    @Autowired
    private WikiHttpClient wikiHttpClient;

    @Autowired
    private ServiceConfigurationProperties serviceConfigurationProperties;

    @Bean
    public FootballClubWikiService footballClubWikiService() {
        return new FootballClubWikiServiceImpl(wikiHttpClient, serviceConfigurationProperties.getFootballClubRegex());
    }
}
