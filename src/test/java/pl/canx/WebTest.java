package pl.canx;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

abstract class WebTest {

    private static final Logger logger = LoggerFactory.getLogger(WebTest.class);

    protected WebDriver driver;
    protected static final int PORT = 8089;
    protected static final String BASE_URL = "localhost:" + PORT;
    private static WireMockServer mockServer;

    @BeforeAll
    private static void beforeAllWebTests() {
        WebDriverManager.chromedriver().setup();

        Awaitility.setDefaultPollInterval(500, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultTimeout(5, TimeUnit.SECONDS);

        logger.info("Starting web server on port: {}", PORT);
        mockServer = new WireMockServer(PORT);
        mockServer.start();
    }

    @AfterAll
    private static void afterAllWebTests() {
        logger.info("Shutting down web server");
        mockServer.stop();
    }

    @BeforeEach
    private void beforeEachWebTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    private void afterEachWebTests() {
        if (driver != null) {
            driver.quit();
        }
    }

}
