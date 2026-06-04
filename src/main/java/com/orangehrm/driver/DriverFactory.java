package com.orangehrm.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver createDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // VERY IMPORTANT
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        // creates fresh temp profile every run
        options.addArguments("--incognito");

        // avoid renderer freezes
        options.addArguments("--disable-features=VizDisplayCompositor");

        // stability
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(60));

        return driver;
    }
}