package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.exceptions.FrameworkException;
import com.orangehrm.interfaces.IPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.tracing.Status;

import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.formula.eval.ErrorEval.getText;

public class LeavePage extends BasePage implements IPage {
    private By dropdownOptions;

    public LeavePage(WebDriver driver) {
        super(driver);
    }

    private final By from_date = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By to_date = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By showLeaveWithStatus = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
    private final By leaveType = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
    private final By employeeName = By.xpath("//input[@placeholder='Type for hints...']");
    private final By subUnit = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[3]");
    private final By includePastEmployees = By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");
    private final By searchBtn = By.xpath("//button[text()=' Search ']");
    /*private final By apply =By.xpath("//a[@class='oxd-topbar-body-nav-tab-item']");
    private final By leavetype =By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']");
    private final By fromdate =By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By todate =By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By Comments =By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']");
    private final By Apply =By.xpath("//button[text()=' Apply']");*/

    public void selectFromCustomDropdown(By dropdown, String optionText) {
        click(dropdown);                                   // open the dropdown
        List<WebElement> options = getElements(dropdownOptions);
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(optionText)) {
                opt.click();
                return;
            }
        }
        throw new FrameworkException("Dropdown option not found: " + optionText);
    }

    public void from_date(String date) {
        driver.findElement(from_date).sendKeys(date);
    }

    public void to_date(String date) {
        driver.findElement(to_date).sendKeys(date);
    }

    public void search_Btn() {
        driver.findElement(searchBtn).click();
    }

    public void include_Past_Employees() {
        driver.findElement(includePastEmployees).click();
    }

    public void set_sub_Unit(String subUnitName) {

        driver.findElement(subUnit).click();

        driver.findElement(
                By.xpath("//div[@role='option']/span[text()='"
                        + subUnitName + "']")).click();
    }

    public void set_employ_name(String name) {

        driver.findElement(employeeName).sendKeys(name);

        driver.findElement(
                By.xpath("//div[@role='option']/span[text()='"
                        + name + "']")).click();
    }

    public void set_leave_type(String type) {

        driver.findElement(leaveType).click();

        driver.findElement(
                By.xpath("//div[@role='option']/span[text()='"
                        + type + "']")).click();
    }

    public void set_leave_with_status(String status) {

        driver.findElement(showLeaveWithStatus).click();

        driver.findElement(
                By.xpath("//div[@role='option']/span[text()='"
                        + status + "']")).click();
    }

//    public void searchUser(By from_date, By to_date, By showLeaveWithStatus, String status, String type, String name, By employeeName, By subUnit, String Sub_unit_name, By includePastEmployees, By searchBtn) {
//        from_date(from_date, "2026-21-05");
//        to_date(to_date, "2026-25-05");
//        set_leave_with_status(showLeaveWithStatus, status);
//        set_leave_type(leaveType, type);
//        set_employ_name(employeeName, name);
//        set_sub_Unit(subUnit, Sub_unit_name);
//        include_Past_Employees(includePastEmployees);
//        search_Btn(searchBtn);
//    }





/*public void resetFilters() { click(resetBtn); }
public String getRecordsFoundText() { return getText(recordsFoundLabel); }*/

    @Override
    public boolean isPageLoaded() {
        return isPageLoaded();
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
        return getPageUrl().contains("/admin");
    }
}

