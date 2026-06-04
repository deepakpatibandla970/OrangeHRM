package com.orangehrm.hooks;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.driver.DriverManager;
import com.orangehrm.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Scenario-scoped lifecycle. A fresh browser is created before every scenario
 * and quit afterwards, which keeps parallel threads isolated. On failure a
 * screenshot is attached to the Allure report.
 */
public class Hooks {

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        DriverManager.setDriver(DriverFactory.createDriver());
    }

    @After(order = 1)
    public void captureOnFailure(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.hasDriver()) {
            byte[] png = ScreenshotUtils.capture("Failure - " + scenario.getName());
            if (png.length > 0) {
                // Also attach to the Cucumber scenario so it appears in the
                // native Cucumber report as well as Allure.
                scenario.attach(png, "image/png", scenario.getName());
            }
        }
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        DriverManager.quitDriver();
    }
}
