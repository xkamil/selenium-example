package pl.canx.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends Page {

    public static final String TITLE = "Dashboard";
    public static final String PATH = "/dashboard.html";

    private final By btnLogout = By.id("btn_logout");

    public DashboardPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public DashboardPage logout() {
        getDriver().findElement(btnLogout).click();
        return this;
    }
}
