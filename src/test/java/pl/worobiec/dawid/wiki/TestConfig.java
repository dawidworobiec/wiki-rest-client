package pl.worobiec.dawid.wiki;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestConfig {

    @Autowired
    public WebTestClient webTestClient;

    @RegisterExtension
    public WireMockExtension wireMockRule = new WireMockExtension(8090);

}
