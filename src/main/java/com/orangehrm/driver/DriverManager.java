package com.orangehrm.driver;

import org.openqa.selenium.WebDriver;

/**
 * Thread-safe holder for the active {@link WebDriver}. Each test thread gets
 * its own driver instance, which is what makes parallel scenario execution safe.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialised for this thread. "
                            + "Did the @Before hook run?");
        }
        return driver;
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }

    public static void quitDriver()
    {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();

        }
    }
}
