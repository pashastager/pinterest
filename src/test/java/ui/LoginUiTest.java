package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pinterest.login.ui.PinterestLoginPageMethods;

import java.time.Duration;

import static org.pinterest.login.ui.PinterestLoginPageMessages.MISSING_EMAIL_ERROR;
import static org.pinterest.login.ui.PinterestLoginPageXpath.*;

public class LoginUiTest {
    @Test
    @DisplayName("Авторизация без email")
    public void errorWitoutLogin() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://ru.pinterest.com");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PinterestLoginPageMethods pinterestLoginPageMethods = new PinterestLoginPageMethods(webDriver);

        pinterestLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        pinterestLoginPageMethods.sendKeysInput(INPUT_PASSWORD_XPATH, "Qwd12345#");
        pinterestLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(pinterestLoginPageMethods.getResultErrorText(ENTER_EMAIL_ERROR_XPATH), MISSING_EMAIL_ERROR);
    }

    @Test
    @DisplayName("Пустой пароль")
    public void errorWitoutPassword() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://ru.pinterest.com");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        OnlinerLoginPageMethods onlinerLoginPageMethods = new OnlinerLoginPageMethods(webDriver);

        onlinerLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        onlinerLoginPageMethods.sendKeysInput(INPUT_LOGIN_XPATH, "Pavelkalinkov");
        onlinerLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(onlinerLoginPageMethods.getResultErrorText(ENTER_PASSWORD_ERROR_XPATH), ENTER_PASSWORD_ERROR);
    }

    @Test
    @DisplayName("Некорректный логин и пароль")
    public void errorLoginAndPassword() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://ru.pinterest.com");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        OnlinerLoginPageMethods onlinerLoginPageMethods = new OnlinerLoginPageMethods(webDriver);

        onlinerLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        onlinerLoginPageMethods.sendKeysInput(INPUT_LOGIN_XPATH, "1223");
        onlinerLoginPageMethods.sendKeysInput(INPUT_PASSWORD_XPATH, "1223");
        onlinerLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(onlinerLoginPageMethods.getResultErrorText(LOGIN_AND_PASSWORD_ERROR_XPATH), LOGIN_AND_PASSWORD_ERROR);
    }
}
