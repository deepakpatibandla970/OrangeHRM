package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminPage extends BasePage {

    private static final By RESULTS_TEXT =
            By.xpath("//span[contains(@class,'oxd-text--span') and contains(.,'Record')]");
    private static final By TABLE_ROWS =
            By.cssSelector(".oxd-table-body .oxd-table-card");
    private static final By TABLE_CELL =
            By.cssSelector(".oxd-table-body .oxd-table-cell");
    private static final By AUTOCOMPLETE_OPTION =
            By.cssSelector(".oxd-autocomplete-dropdown [role='option']");
    private static final By PAGE_HEADER =
            By.cssSelector(".oxd-table-filter-title");

    public boolean isLoaded() {
        return WaitUtils.urlContains("admin") && isDisplayed(PAGE_HEADER);
    }

    @Step("Search for system user with username '{username}'")
    public AdminPage searchUser(String username) {
        typeInFieldByLabel("Username", username);
        clickButtonByText("Search");
        waitForLoaderToDisappear();
        return this;
    }

    public void filterByRole(String role) {

        waitForLoaderToDisappear();

        selectDropdownByLabel("User Role", role);

        clickButtonByText("Search");

        waitForLoaderToDisappear();

    }
    @Step("Read the available User Role options")
    public List<String> getUserRoleOptions() throws InterruptedException {

        return getDropdownOptions("User Role");
    }

    @Step("Reset the Admin search filters")
    public AdminPage resetSearch() {
        clickButtonByText("Reset");
        waitForLoaderToDisappear();
        return this;
    }

    @Step("Read the results-found summary text")
    public String getResultsText() {
        return getText(RESULTS_TEXT);
    }

    @Step("Count the result rows in the table")
    public int getResultRowCount() {
        return count(TABLE_ROWS);
    }

    @Step("Check whether the results table contains '{value}'")
    public boolean tableContains(String value) {
        if (count(TABLE_CELL) == 0) {
            return false;
        }
        return WaitUtils.allVisible(TABLE_CELL).stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.contains(value));
    }

    @Step("Open the Add User form")
    public AdminPage clickAdd() {
        clickButtonByText("Add");
        waitForLoaderToDisappear();
        return this;
    }

    @Step("Create a system user (role={role}, status={status}, username={username})")
    public AdminPage addUser(String role, String employeeName, String status,
                             String username, String password) {
        selectDropdownByLabel("User Role", role);
        selectDropdownByLabel("Status", status);

        // Employee Name is an autocomplete field.
        typeInFieldByLabel("Employee Name", employeeName);
        WaitUtils.clickable(AUTOCOMPLETE_OPTION).click();

        typeInFieldByLabel("Username", username);

        // The two password inputs share the "Password" / "Confirm Password" labels.
        typeInFieldByLabel("Password", password);
        typeInFieldByLabel("Confirm Password", password);

        clickButtonByText("Save");
        waitForLoaderToDisappear();
        return this;
    }
}
