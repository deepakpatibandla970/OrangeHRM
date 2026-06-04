package com.orangehrm.utils;

import com.orangehrm.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Screenshot capture that feeds directly into the Allure report. The
 * {@link Attachment} annotation makes the returned bytes show up inline on the
 * test in the generated report.
 */
public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] capture(String name) {
        if (!DriverManager.hasDriver()) {
            return new byte[0];
        }
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
