package pl.canx.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    public static final String TITLE = "Dashboard";

    private final By btnLogout = By.id("btn_logout");
    private final WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public DashboardPage logout() {
        driver.findElement(btnLogout).click();
        return this;
    }
}
