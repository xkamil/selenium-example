package pl.canx;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

abstract class WebTest {
    private static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        // Disable htmldriver warnings
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        Awaitility.setDefaultPollInterval(500, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultTimeout(5, TimeUnit.SECONDS);

        driver = new HtmlUnitDriver(true);
    }

    protected static WebDriver getDriver() {
        return driver;
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
