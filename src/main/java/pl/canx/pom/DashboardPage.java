package pl.canx.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends Page {

    public static final String TITLE = "Dashboard";
    public static final String PATH = "/login.html";

    private final By btnLogout = By.id("btn_logout");
    private final By userLabel = By.id("user_label");

    public DashboardPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public void open() {
        getDriver().get(getBaseUrl() + PATH);
    }

    public void clickLogoutButton() {
        getDriver().findElement(btnLogout).click();
    }

    public String getLoggedInUsername() {
        return getDriver().findElement(userLabel).getText();
    }
}
