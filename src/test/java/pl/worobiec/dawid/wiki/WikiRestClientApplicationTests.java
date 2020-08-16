package pl.worobiec.dawid.wiki;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
class WikiRestClientApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        Assert.notNull(context, "Context should loaded");
    }

}
