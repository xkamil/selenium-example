package pl.canx.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

    public static final String TITLE = "Login page";
    public static final String PATH = "/login.html";

    private final By inputUsername = By.id("input_username");
    private final By inputPassword = By.id("input_password");
    private final By btnLogin = By.id("btn_login");
    private final By errorMessage = By.id("error_message");
    private final By checkboxRememberMe = By.id("remember_me");

    public LoginPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public void open() {
        getDriver().get(getBaseUrl() + PATH);
    }

    public void setUsername(String username) {
        getDriver().findElement(inputUsername).sendKeys(username);
    }

    public String getUsername() {
        return getDriver().findElement(inputUsername).getAttribute("value");
    }


    public void setPassword(String password) {
        getDriver().findElement(inputPassword).sendKeys(password);
    }

    public String getPassword() {
        return getDriver().findElement(inputPassword).getAttribute("value");
    }

    public void setRememberMe(boolean rememberMe) {
        WebElement checkbox = getDriver().findElement(checkboxRememberMe);

        if ((checkbox.isSelected() && !rememberMe) || (!checkbox.isSelected() && rememberMe)) {
            checkbox.click();
        }
    }

    public boolean isRememberMeChecked() {
        return getDriver().findElement(checkboxRememberMe).isSelected();
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
