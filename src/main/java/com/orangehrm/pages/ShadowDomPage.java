package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDomPage {

    WebDriver driver;

    public ShadowDomPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSearchIcon() {

        // first shadow host
        WebElement app =
                driver.findElement(By.cssSelector("shop-app"));

        SearchContext shadowRoot1 = app.getShadowRoot();

        // toolbar
        WebElement toolbar =
                shadowRoot1.findElement(By.cssSelector("app-toolbar"));

        // search button inside shadow dom
        return toolbar.findElement(By.cssSelector("a[href='/list/mens_outerwear']"));
    }
}