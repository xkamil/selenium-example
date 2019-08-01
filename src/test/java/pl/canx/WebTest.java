package pl.canx;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

abstract class WebTest {

    protected WebDriver driver;

    @BeforeAll
    private static void beforeAllWebTests() {
        WebDriverManager.chromedriver().setup();
        Awaitility.setDefaultPollInterval(500, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultTimeout(5, TimeUnit.SECONDS);
    }

    @BeforeEach
    private void beforeEachWebTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    private void afterAllWebTests() {
        if (driver != null) {
            driver.quit();
        }
    }

}
