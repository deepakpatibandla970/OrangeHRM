package com.orangehrm.utils;

import com.orangehrm.config.ConfigReader;
import com.orangehrm.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Thin wrapper over WebDriverWait so page objects never deal with
 * raw timeouts or Thread.sleep().
 */
public final class WaitUtils {

    private WaitUtils() {
    }

    private static WebDriverWait wait1() {
        WebDriver driver = DriverManager.getDriver();

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.explicitWait())
        );
    }

    public static WebElement clickable(By locator) {
        return wait1().until(ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement visible(By locator) {

        invisibility(By.cssSelector(".oxd-loading-spinner, .oxd-form-loader"));

        return wait1().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static List<WebElement> allVisible(By locator) {
        return wait1().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }

    public static boolean isVisible(By locator) {
        try {
            visible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean urlContains(String fragment) {
        try {
            return wait1().until(
                    ExpectedConditions.urlContains(fragment)
            );
        } catch (Exception e) {
            return false;
        }
    }

    public static void invisibility(By locator) {
        try {
            wait1().until(
                    ExpectedConditions.invisibilityOfElementLocated(locator)
            );
        } catch (Exception ignored) {
            // element already disappeared
        }
    }
}