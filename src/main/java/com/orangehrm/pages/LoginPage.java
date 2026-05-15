package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.interfaces.IPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage implements IPage {

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    private final By errorAlert =
            By.xpath("//p[text()='Invalid credentials']");

    private final By requiredMsg =
            By.cssSelector(".oxd-input-field-error-message");

    private final By logo =
            By.cssSelector(".orangehrm-login-branding > img");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ACTIONS
    public DashboardPage loginAs(String user, String pass) {

        type(usernameInput, user);
        type(passwordInput, pass);
        click(loginButton);

        return new DashboardPage(driver);
    }

    public String getErrorMessage() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorAlert)
        );

        return driver.findElement(errorAlert).getText();
    }

    public String getRequiredMessage() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(requiredMsg)
        );

        return driver.findElement(requiredMsg).getText();
    }

    // OVERRIDES
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(usernameInput);
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public boolean isAt() {
        return isPageLoaded();
    }
}