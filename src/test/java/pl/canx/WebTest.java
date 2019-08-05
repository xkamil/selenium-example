package pl.canx;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

abstract class WebTest {

    private static final Logger logger = LoggerFactory.getLogger(WebTest.class);
    private static final int PORT = 8089;

    protected static final String BASE_URL = "localhost:" + PORT;
    protected static WebDriver driver;
    private static WireMockServer mockServer;

    @BeforeAll
    private static void beforeAllWebTests() {
        WebDriverManager.chromedriver().setup();

        Awaitility.setDefaultPollInterval(500, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultTimeout(5, TimeUnit.SECONDS);

        logger.info("Starting web server on port: {}", PORT);
        mockServer = new WireMockServer(PORT);
        mockServer.start();

        driver = new ChromeDriver();
    }

    @AfterAll
    private static void afterAllWebTests() {
        driver.quit();
        logger.info("Shutting down web server");
        mockServer.stop();
    }

    @AfterEach
    private void afterEachWebTests() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear()");
        js.executeScript("window.sessionStorage.clear();");
        driver.manage().deleteAllCookies();
    }

}
