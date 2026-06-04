package com.orangehrm.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Entry point picked up by the TestNG suite. Glue points at both the step
 * definitions and the hooks package. The Allure plugin emits results that the
 * allure-maven plugin renders into the HTML report.
 * <p>Use tags from the command line, e.g.
 * {@code mvn test -Dcucumber.filter.tags="@login"}.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.orangehrm.stepdefinitions", "com.orangehrm.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Marking the data provider parallel lets TestNG run scenarios across
     * threads. The actual thread count is controlled by
     * {@code data-provider-thread-count} in testng.xml (or the matching
     * {@code -Ddataproviderthreadcount} system property).
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
