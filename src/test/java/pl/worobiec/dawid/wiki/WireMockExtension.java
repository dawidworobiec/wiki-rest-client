package pl.worobiec.dawid.wiki;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class WireMockExtension extends WireMockServer implements BeforeEachCallback, AfterEachCallback {

    public WireMockExtension(int port) {
        super(port);
        WireMock.configureFor("localhost", port);
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        this.start();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        this.stop();
        this.resetAll();
    }
}