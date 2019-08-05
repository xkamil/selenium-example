package pl.canx.pom;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    private final String baseUrl;
    private final WebDriver driver;

    public Page(String baseUrl, WebDriver driver) {
        this.baseUrl = baseUrl;
        this.driver = driver;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
