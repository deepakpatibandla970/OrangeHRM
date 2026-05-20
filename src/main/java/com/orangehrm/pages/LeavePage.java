package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.exceptions.FrameworkException;
import com.orangehrm.interfaces.IPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LeavePage extends BasePage implements IPage {

    public LeavePage(WebDriver driver) {
        super(driver);
    }

    // ---------- Locators ----------

    private final By from_date =
            By.xpath("//label[text()='From Date']/ancestor::div[contains(@class,'oxd-input-group')]//input");

    private final By to_date =
            By.xpath("//label[text()='To Date']/ancestor::div[contains(@class,'oxd-input-group')]//input");

    private final By showLeaveWithStatus =
            By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[1]");

    private final By leaveType =
            By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[2]");

    private final By employeeName =
            By.xpath("//input[@placeholder='Type for hints...']");

    private final By subUnit =
            By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[3]");

    private final By includePastEmployees =
            By.xpath("//span[contains(@class,'oxd-switch-input')]");

    private final By searchBtn =
            By.xpath("//button[normalize-space()='Search']");

    private final By dropdownOptions =
            By.xpath("//div[@role='option']/span");

    // ---------- Methods ----------

    public void selectFromCustomDropdown(By dropdown, String optionText) {

        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

        List<WebElement> options =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));

        for (WebElement opt : options) {

            if (opt.getText().trim().equalsIgnoreCase(optionText)) {
                opt.click();
                return;
            }
        }

        throw new FrameworkException("Dropdown option not found: " + optionText);
    }

    public void from_date(String date) {

        WebElement from =
                wait.until(ExpectedConditions.visibilityOfElementLocated(from_date));

        from.clear();
        from.sendKeys(date);
    }

    public void to_date(String date) {

        WebElement to =
                wait.until(ExpectedConditions.visibilityOfElementLocated(to_date));

        to.clear();
        to.sendKeys(date);
    }

    public void search_Btn() {

        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public void include_Past_Employees() {

        wait.until(ExpectedConditions.elementToBeClickable(includePastEmployees)).click();
    }

    public void set_sub_Unit(String subUnitName) {

        wait.until(ExpectedConditions.elementToBeClickable(subUnit)).click();

        By option = By.xpath("//div[@role='option']/span[text()='"
                + subUnitName + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void set_employ_name(String name) {

        WebElement emp =
                wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));

        emp.clear();
        emp.sendKeys(name);

        By option = By.xpath("//div[@role='option']/span[text()='"
                + name + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void set_leave_type(String type) {

        wait.until(ExpectedConditions.elementToBeClickable(leaveType)).click();

        By option = By.xpath("//div[@role='option']/span[text()='"
                + type + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void set_leave_with_status(String status) {

        wait.until(ExpectedConditions.elementToBeClickable(showLeaveWithStatus)).click();

        By option = By.xpath("//div[@role='option']/span[text()='"
                + status + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    // ---------- IPage Methods ----------

    @Override
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("/leave");
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public boolean isAt() {
        return getPageUrl().contains("/leave");
    }
}