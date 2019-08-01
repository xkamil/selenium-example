package pl.canx.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public static final String TITLE = "Login page";
    public static final String URL = "http://localhost/login.html";

    private final WebDriver driver;
    private final By inputUsername = By.id("input_username");
    private final By inputPassword = By.id("input_password");
    private final By btnLogin = By.id("btn_login");
    private final By errorMessage = By.id("error_message");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public LoginPage setUsername(String username) {
        driver.findElement(inputUsername).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        driver.findElement(btnLogin).click();
    }

    public boolean isErrorMessageVisible() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

}
