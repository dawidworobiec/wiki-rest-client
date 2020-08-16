package pl.worobiec.dawid.wiki.config.http;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import pl.worobiec.dawid.wiki.client.WikiHttpClient;
import pl.worobiec.dawid.wiki.config.WikiHttpClientProperties;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties({HttpClientConfigurationProperties.class, WikiHttpClientProperties.class})
public class HttpClientConfiguration {

    private final HttpClientConfigurationProperties httpClientConfigurationProperties;
    private final WikiHttpClientProperties wikiHttpClientProperties;

    public HttpClientConfiguration(
            HttpClientConfigurationProperties httpClientConfigurationProperties,
            WikiHttpClientProperties wikiHttpClientProperties
    ) {
        this.httpClientConfigurationProperties = httpClientConfigurationProperties;
        this.wikiHttpClientProperties = wikiHttpClientProperties;
    }

    public TcpClient tcpClient() {
        return TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, httpClientConfigurationProperties.getConnectionTimeout())
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(httpClientConfigurationProperties.getReadTimeout(), TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient webClient() {
        TcpClient tcpClient = tcpClient();
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    @Bean
    public WikiHttpClient wikiHttpClient() {
        var wikiWebClient = webClient();
        return new WikiHttpClient(wikiWebClient, wikiHttpClientProperties);
    }
}
