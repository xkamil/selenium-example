package pl.canx;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.canx.pom.DashboardPage;
import pl.canx.pom.LoginPage;

import static com.google.common.truth.Truth.assertThat;
import static org.awaitility.Awaitility.await;

class LoginTests extends WebTest {

    private static LoginPage loginPage;

    @BeforeAll
    static void beforeAllLoginTests() {
        loginPage = new LoginPage(getDriver());
    }

    @DisplayName("User should be logged in using valid credentials")
    @ParameterizedTest(name = "User {0} should log in using valid password: {1}")
    @CsvSource({
            "david, pass1",
            "roman, pass2"
    })
    void testLoginValidCredentials(String username, String password) {

        // given I'm on login page
        loginPage.open();

        // when I enter valid username and password
        loginPage.setUsername(username);
        loginPage.setPassword(password);

        // and I click login button
        loginPage.clickLoginButton();

        // then I should be logged in
        await().untilAsserted(() -> {
            assertThat(getDriver().getTitle()).isEqualTo(DashboardPage.TITLE);
        });
    }

    @DisplayName("User should not be logged in using invalid credentials")
    @Test
    void testLoginInalidCredentials() {
        // given I'm on login page
        loginPage.open();

        // when I enter invalid username and password
        loginPage.setUsername("asdfdasf");
        loginPage.setPassword("fasdf");

        // and I click login button
        loginPage.clickLoginButton();

        // then I should see error message
        assertThat(loginPage.isErrorMessageVisible()).isTrue();
        assertThat(loginPage.getErrorMessage()).isEqualTo("Invalid credentials provided.");
    }

    @DisplayName("User should not be logged with empty username or password")
    @ParameterizedTest(name = "User should not be logged in using {0}")
    @CsvSource({
            "'empty username', '', 'pass1', 'Username is required'",
            "'empty password', 'david', '', 'Password is required'"
    })
    void testLoginEmptyUsernameOrPassword(String description, String username, String password, String errorMessage) {
        // given I'm on login page
        loginPage.open();

        // when I enter invalid username and password
        loginPage.setUsername(username);
        loginPage.setPassword(password);

        // and I click login button
        loginPage.clickLoginButton();

        // then I should see error message
        assertThat(loginPage.isErrorMessageVisible()).isTrue();
        assertThat(loginPage.getErrorMessage()).isEqualTo(errorMessage);
    }


}

