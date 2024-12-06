package org.pinterest.login.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PinterestLoginPageMethods {
    private WebDriver webDriver;

    public PinterestLoginPageMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickButton(String button) {

        By by = By.xpath(button);
        WebElement webElement = webDriver.findElement(by);
        webElement.click();
    }

    public void sendKeysInput(String field, String value) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        By inputNameBy = By.xpath(field);
        WebElement inputWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(inputNameBy));
        inputWebElement.findElement(inputNameBy);
        inputWebElement.sendKeys(value);
    }

    public String getResultErrorText(String textXpath) {
        By textResultErrorBy = By.xpath(textXpath);
        WebElement textResultErrorWebElement = webDriver.findElement(textResultErrorBy);
        return textResultErrorWebElement.getText();
    }
}
