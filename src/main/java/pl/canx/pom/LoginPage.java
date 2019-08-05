package pl.canx.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public static final String TITLE = "Login page";
    public static final String PATH = "/login.html";

    private final By inputUsername = By.id("input_username");
    private final By inputPassword = By.id("input_password");
    private final By btnLogin = By.id("btn_login");
    private final By errorMessage = By.id("error_message");

    public LoginPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public LoginPage open() {
        getDriver().get(getBaseUrl() + PATH);
        return this;
    }

    public LoginPage setUsername(String username) {
        getDriver().findElement(inputUsername).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        getDriver().findElement(inputPassword).sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        getDriver().findElement(btnLogin).click();
    }

    public boolean isErrorMessageVisible() {
        return getDriver().findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return getDriver().findElement(errorMessage).getText();
    }

}
