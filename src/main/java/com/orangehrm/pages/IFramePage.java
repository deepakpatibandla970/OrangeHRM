package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.interfaces.IPage;
import org.openqa.selenium.*;

/**
 * IFRAME demonstration.
 * OrangeHRM occasionally embeds iframes (e.g. Help docs, embedded reports).
 * For a stable demo, this page wraps any URL containing an iframe and
 * shows: switch INTO frame -> read text -> switch BACK to default.
 * For learning, point the test at:  https://the-internet.herokuapp.com/iframe
 */
public class IFramePage extends BasePage implements IPage {

    private final By frameLocator     = By.id("mce_0_ifr");
    private final By bodyInsideIframe = By.id("tinymce");

    public IFramePage(WebDriver driver) { super(driver); }

   /* public void writeInsideIframe(String text) {
        switchToFrame(frameLocator);                                 // step into
        try {
            driver.findElement(bodyInsideIframe).clear();
            driver.findElement(bodyInsideIframe).sendKeys(text);
        } finally {
            switchToDefaultContent();                                // ALWAYS step out
        }
    }*/
   public void writeInsideIframe(String text) {

       switchToFrame(frameLocator);

       try {

           JavascriptExecutor js = (JavascriptExecutor) driver;

           js.executeScript(
                   "arguments[0].innerHTML = '" + text + "';",
                   driver.findElement(bodyInsideIframe)
           );

       } finally {

           switchToDefaultContent();
       }
   }

    public String readIframeContent() {
        switchToFrame(frameLocator);
        try {
            return driver.findElement(bodyInsideIframe).getText();
        } finally {
            switchToDefaultContent();
        }
    }

    @Override public boolean isPageLoaded() { return isDisplayed(frameLocator); }
    @Override public String getPageTitle()  { return driver.getTitle(); }
    @Override public String getPageUrl()    { return driver.getCurrentUrl(); }
    @Override public boolean isAt()         { return isPageLoaded(); }
}
