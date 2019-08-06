package pl.canx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.canx.pom.DashboardPage;
import pl.canx.pom.LoginPage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.TimeUnit;

import static com.google.common.truth.Truth.assertThat;
import static org.awaitility.Awaitility.await;

class AuthenticationTests extends WebTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void beforeEach() {
        loginPage = new LoginPage(BASE_URL, driver);
        dashboardPage = new DashboardPage(BASE_URL, driver);
    }

    @DisplayName("User david should be logged in using valid password")
    @Test
    void testUserDavidCanLogIn() {
        // given I'm on login page
        loginPage.open();

        // when I enter valid username and password
        loginPage.setUsername("david");
        loginPage.setPassword("david1");

        // and I click login button
        loginPage.clickLoginButton();

        // then I should log in
        await().timeout(1, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(driver.getTitle()).isEqualTo(DashboardPage.TITLE));
    }

    @DisplayName("User should not be logged in using invalid credentials")
    @Test
    void testLoginWithInvalidCredentials() {
        // given I'm on login page
        loginPage.open();

        // when I enter valid username and invalid password
        loginPage.setUsername("david");
        loginPage.setPassword("david2");

        // and I click login button
        loginPage.clickLoginButton();

        // then I should see error message
        assertThat(loginPage.isErrorMessageVisible()).isTrue();
        assertThat(loginPage.getErrorMessage()).isEqualTo("Wrong username or password");
    }


    @DisplayName("User should not be logged in using valid username and empty password")
    @Test
    void testLoginWithNoPassword() {
        // given I'm on login page
        loginPage.open();

        // when I enter valid username and no password
        loginPage.setUsername("david");

        // and I click login button
        loginPage.clickLoginButton();

        // then I should see error message
        assertThat(loginPage.isErrorMessageVisible()).isTrue();
        assertThat(loginPage.getErrorMessage()).isEqualTo("Password is required");
    }

    @DisplayName("User should not be logged in using no username and some valid password")
    @Test
    void testLoginWithNoUsername() {
        throw new NotImplementedException();
    }

    @DisplayName("User credentials should be filled if user checked `Remember me' on last successful login")
    @Test
    void testUserCredentialsFilled() {
        // given I was successful logged in with 'remember me' checked
        loginPage.open();

        loginPage.setUsername("david");
        loginPage.setPassword("david1");
        loginPage.setRememberMe(true);
        loginPage.clickLoginButton();

        await().timeout(2, TimeUnit.SECONDS).untilAsserted(
                () -> assertThat(driver.getTitle()).isEqualTo(DashboardPage.TITLE));

        dashboardPage.clickLogoutButton();

        await().untilAsserted(() -> assertThat(driver.getTitle()).isEqualTo(LoginPage.TITLE));

        // then I should see credentials filled in and 'remember me' checked
        assertThat(loginPage.getUsername()).isEqualTo("david");
        assertThat(loginPage.getPassword()).isEqualTo("david1");
        assertThat(loginPage.isRememberMeChecked()).isTrue();
    }

    @DisplayName("Logged in username should display on dashboard page")
    @Test
    void testUsernameDisplayed() {
        throw new NotImplementedException();
        // given I'm on login page

        // when I enter valid username and password

        // and I click login button

        // then I should log in

        // and my username should display
    }

    @DisplayName("Logged in username should logout after clicking logout button")
    @Test
    void testLoggingOut() {
        // given I'm logged in
        loginPage.open();
        loginPage.setUsername("david");
        loginPage.setPassword("david1");
        loginPage.clickLoginButton();

        await().timeout(2, TimeUnit.SECONDS).untilAsserted(
                () -> assertThat(driver.getTitle()).isEqualTo(DashboardPage.TITLE));

        // when I click logout button
        dashboardPage.clickLogoutButton();

        // then I should see login page
        await().untilAsserted(() -> assertThat(driver.getTitle()).isEqualTo(LoginPage.TITLE));
    }

    @DisplayName("Not logged in user should se login page if navigating to dashboard")
    @Test
    void testUnauthorizedUserCantSeeDashboard() {
        // given I'm not logged in, when I open dashboard page
        dashboardPage.open();

        // then I should see login page
        await().untilAsserted(() -> assertThat(driver.getTitle()).isEqualTo(DashboardPage.TITLE));
    }

}

