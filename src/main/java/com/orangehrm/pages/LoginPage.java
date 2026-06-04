package com.orangehrm.pages;

import com.orangehrm.config.ConfigReader;
import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {

    private static final By USERNAME      = By.name("username");
    private static final By PASSWORD      = By.name("password");
    private static final By LOGIN_BUTTON  = By.cssSelector("button[type='submit']");
    private static final By ALERT_MESSAGE = By.cssSelector(".oxd-alert-content-text");
    private static final By FIELD_ERRORS  = By.cssSelector(".oxd-input-field-error-message");
    private static final By FORGOT_LINK   = By.xpath("//p[text()='Forgot your password? ']");
    private static final By LOGIN_BRANDING = By.cssSelector(".orangehrm-login-branding");
    //private static final By RESET_PASSWORD = By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']");

    @Step("Open the OrangeHRM login page")
    public LoginPage open() {
        driver.get(ConfigReader.baseUrl());
        WaitUtils.visible(USERNAME);
        return this;
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        type(USERNAME, username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        type(PASSWORD, password);
    }

    @Step("Click the Login button")
    public void clickLogin() {
        click(LOGIN_BUTTON);
    }

    @Step("Log in as {username}")
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new DashboardPage();
    }

    @Step("Log in with the configured admin account")
    public DashboardPage loginAsAdmin() {
        return login(ConfigReader.adminUsername(), ConfigReader.adminPassword());
    }

    @Step("Read the login alert message")
    public String getAlertMessage() {
        return getText(ALERT_MESSAGE);
    }

    @Step("Read the required-field validation messages")
    public List<String> getFieldErrorMessages() {
        return WaitUtils.allVisible(FIELD_ERRORS).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    @Step("Click the 'Forgot your password?' link")
    public void clickForgotPassword() {
        click(FORGOT_LINK);
        /*WaitUtils.allVisible(RESET_PASSWORD).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();*/
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(LOGIN_BRANDING) && isDisplayed(USERNAME);
    }

    public boolean isAlertDisplayed() {
        return isDisplayed(ALERT_MESSAGE);
    }
}
