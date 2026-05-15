package com.orangehrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShadowDOMTest {

    @Test
    public void testShadowDom() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://shop.polymer-project.org/");

        // Locate shadow host
        WebElement shopApp =
                driver.findElement(By.cssSelector("shop-app"));

        // Get shadow root
        SearchContext shadowRoot =
                shopApp.getShadowRoot();

        // Get toolbar text element
        WebElement header =
                shadowRoot.findElement(By.cssSelector("app-header"));

        System.out.println("Shadow DOM accessed successfully");

        System.out.println(header.getText());

        driver.quit();
    }
}