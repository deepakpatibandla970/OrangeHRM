package com.orangehrm.pages;

import com.orangehrm.driver.DriverManager;
import com.orangehrm.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base class for every page object. Holds shared element interactions plus
 * helpers tailored to the OrangeHRM "OXD" front-end framework.
 */
public abstract class BasePage {

    protected final WebDriver driver;

    // Loading spinner shown by OrangeHRM during async data loads.
    private static final By LOADER =
            By.cssSelector(".oxd-loading-spinner, .oxd-form-loader");

    protected BasePage() {

        this.driver = DriverManager.getDriver();
    }

    // ---------------------------------------------------------------------
    // Generic interactions
    // ---------------------------------------------------------------------

    protected void click(By locator) {

        WebElement element = WaitUtils.visible(locator);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {

        WebElement el = WaitUtils.visible(locator);

        el.clear();

        el.sendKeys(text);
    }

    protected String getText(By locator) {

        return WaitUtils.visible(locator)
                .getText()
                .trim();
    }

    protected boolean isDisplayed(By locator) {

        return WaitUtils.isVisible(locator);
    }

    protected int count(By locator) {

        return driver.findElements(locator).size();
    }

    // ---------------------------------------------------------------------
    // OrangeHRM / OXD specific helpers
    // ---------------------------------------------------------------------

    /**
     * Click button by visible text.
     */
    protected void clickButtonByText(String text) {

        By button =
                By.xpath("//button[normalize-space()='" + text + "']");

        WebElement element = WaitUtils.visible(button);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    /**
     * Type into field using label text.
     */
    public void typeInFieldByLabel(String label, String value) {

        By field = By.xpath(
                "//label[normalize-space()='" + label + "']" +
                        "/ancestor::div[contains(@class,'oxd-input-group')]" +
                        "//input"
        );

        WebElement input = WaitUtils.visible(field);

        input.clear();

        input.sendKeys(value);
    }

    public void selectDropdownByLabel(String label, String option) {

        By dropdown = By.xpath(
                "//label[normalize-space()='" + label + "']" +
                        "/ancestor::div[contains(@class,'oxd-input-group')]" +
                        "//div[contains(@class,'oxd-select-text')]"
        );

        waitForLoaderToDisappear();

        WebElement dropdownElement = WaitUtils.clickable(dropdown);

        dropdownElement.click();

        By optionLocator = By.xpath(
                "//div[@role='listbox']//span[normalize-space()='" + option + "']"
        );

        WebElement optionElement = WaitUtils.clickable(optionLocator);

        optionElement.click();

        waitForLoaderToDisappear();
    }

    /**
     * Get all dropdown option texts.
     */
    public List<String> getDropdownOptions(String label) {

        By dropdown = By.xpath(
                "//label[normalize-space()='" + label + "']" +
                        "/ancestor::div[contains(@class,'oxd-input-group')]" +
                        "//div[contains(@class,'oxd-select-text')]"
        );

        waitForLoaderToDisappear();

        WebElement dropdownElement = WaitUtils.clickable(dropdown);

        // scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdownElement
        );

        // use Actions click instead of JS click
        new org.openqa.selenium.interactions.Actions(driver)
                .moveToElement(dropdownElement)
                .click()
                .perform();

        // wait for options directly
        By options = By.xpath(
                "//div[@role='option']//span"
        );

        List<WebElement> optionElements =
                WaitUtils.allVisible(options);

        List<String> values = optionElements.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .distinct()
                .toList();

        // click outside to close dropdown
        ((JavascriptExecutor) driver)
                .executeScript("document.body.click();");

        waitForLoaderToDisappear();

        return values;
    }

    /**
     * Wait for OrangeHRM loader to disappear.
     */
    protected void waitForLoaderToDisappear() {

        WaitUtils.invisibility(LOADER);
    }

    /**
     * Verify element becomes clickable.
     */
    protected boolean waitClickable(By locator) {

        try {

            new WebDriverWait(driver, Duration.ofSeconds(150))
                    .until(
                            ExpectedConditions.elementToBeClickable(locator)
                    );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    /*public String getToastMessage()*/

}