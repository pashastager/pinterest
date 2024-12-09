package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pinterest.login.ui.PinterestLoginPageMethods;

import static org.pinterest.login.ui.PinterestLoginPageMessages.*;
import static org.pinterest.login.ui.PinterestLoginPageXpath.*;

public class LoginUiTest extends Driver {
    @Test
    @DisplayName("Авторизация без email")
    public void errorWitoutLogin() {
        webDriver.get("https://ru.pinterest.com");
        PinterestLoginPageMethods pinterestLoginPageMethods = new PinterestLoginPageMethods(webDriver);

        pinterestLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        pinterestLoginPageMethods.sendKeysInput(INPUT_PASSWORD_XPATH, "Qwd12345#");
        pinterestLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(pinterestLoginPageMethods.getResultErrorText(ENTER_EMAIL_ERROR_XPATH), MISSING_EMAIL_ERROR);
    }

    @Test
    @DisplayName("Авторизация с некорректным email")
    public void errorEmail() {
        webDriver.get("https://ru.pinterest.com");
        PinterestLoginPageMethods pinterestLoginPageMethods = new PinterestLoginPageMethods(webDriver);

        pinterestLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        pinterestLoginPageMethods.sendKeysInput(INPUT_LOGIN_XPATH, "12345");
        pinterestLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(pinterestLoginPageMethods.getResultErrorText(ENTER_EMAIL_ERROR_XPATH), INCORRECT_EMAIL_ERROR);
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void errorWitoutPassword() {
        webDriver.get("https://ru.pinterest.com");
        PinterestLoginPageMethods pinterestLoginPageMethods = new PinterestLoginPageMethods(webDriver);

        pinterestLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        pinterestLoginPageMethods.sendKeysInput(INPUT_LOGIN_XPATH, "12345@gmail.com");
        pinterestLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(pinterestLoginPageMethods.getResultErrorText(ENTER_PASSWORD_ERROR_XPATH), ENTER_PASSWORD_ERROR);
    }

    @Test
    @DisplayName("Некорректный логин и пароль")
    public void errorLoginAndPassword() {
        webDriver.get("https://ru.pinterest.com");
        PinterestLoginPageMethods pinterestLoginPageMethods = new PinterestLoginPageMethods(webDriver);

        pinterestLoginPageMethods.clickButton(OPEN_LOGIN_PAGE_BUTTON_XPATH);
        pinterestLoginPageMethods.clickButton(LOGIN_BUTTON_XPATH);
        Assertions.assertEquals(pinterestLoginPageMethods.getResultErrorText(ENTER_EMAIL_ERROR_XPATH), MISSING_EMAIL_ERROR);
    }
}