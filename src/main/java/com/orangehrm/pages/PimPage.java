package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PimPage extends BasePage {

    private static final By FIRST_NAME =
            By.cssSelector("input[name='firstName']");
    private static final By LAST_NAME =
            By.cssSelector("input[name='lastName']");
    private static final By TOAST =
            By.cssSelector(".oxd-toast");
    private static final By RESULTS_TEXT =
            By.xpath("//span[contains(@class,'oxd-text--span') and contains(.,'Record')]");
    private static final By TABLE_ROWS =
            By.cssSelector(".oxd-table-body .oxd-table-card");
    private static final By PERSONAL_DETAILS_HEADER =
            By.xpath("//h6[normalize-space()='Personal Details']");
    private static final By AUTOCOMPLETE_OPTION =
            By.cssSelector(".oxd-autocomplete-dropdown [role='option']");

    public boolean isLoaded() {
        return WaitUtils.urlContains("pim");
    }

    @Step("Open the Add Employee form")
    public void clickAddEmployee() {
        clickButtonByText("Add");
        waitForLoaderToDisappear();
    }

    @Step("Add employee {firstName} {lastName}")
    public void addEmployee(String firstName, String lastName) {
        type(FIRST_NAME, firstName);
        type(LAST_NAME, lastName);
        clickButtonByText("Save");
        waitForLoaderToDisappear();
    }

    @Step("Confirm the employee personal-details page is shown")
    public boolean isOnPersonalDetails() {
        return isDisplayed(PERSONAL_DETAILS_HEADER);
    }

    @Step("Search for employee by name '{name}'")
    public void searchEmployeeByName(String name) {
        typeInFieldByLabel("Employee Name", name);
        // OrangeHRM requires picking a suggestion; free text triggers a
        // validation error. Select the first real suggestion if one appears.
        if (waitClickable(AUTOCOMPLETE_OPTION)) {
            click(AUTOCOMPLETE_OPTION);
        }
        clickButtonByText("Search");
        waitForLoaderToDisappear();
    }

    @Step("Reset the PIM search filters")
    public void resetSearch() {
        clickButtonByText("Reset");
        waitForLoaderToDisappear();
    }

    @Step("Read the results-found summary text")
    public String getResultsText() {
        return getText(RESULTS_TEXT);
    }

    @Step("Count the employee rows")
    public int getResultRowCount() {
        return count(TABLE_ROWS);
    }

    @Step("Read the confirmation toast message")
    public String getToastMessage() {
        WebElement toast = WaitUtils.visible(TOAST);
        return toast.getText().trim();
    }

}
