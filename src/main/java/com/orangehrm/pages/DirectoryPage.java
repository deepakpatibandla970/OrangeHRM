package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DirectoryPage extends BasePage {

    private static final By DIRECTORY_CARDS =
            By.cssSelector(".orangehrm-directory-card");
    private static final By RESULTS_TEXT =
            By.xpath("//span[contains(@class,'oxd-text--span') and contains(.,'Record')]");
    private static final By AUTOCOMPLETE_OPTION =
            By.cssSelector(".oxd-autocomplete-dropdown [role='option']");
    private static final By FILTER_TITLE =
            By.cssSelector(".oxd-table-filter-title");

    public boolean isLoaded() {
        return WaitUtils.urlContains("directory") && isDisplayed(FILTER_TITLE);
    }

    @Step("Search the directory for '{name}'")
    public DirectoryPage searchByName(String name) {
        typeInFieldByLabel("Name", name);
        if (waitClickable(AUTOCOMPLETE_OPTION)) {
            click(AUTOCOMPLETE_OPTION);
        }
        clickButtonByText("Search");
        waitForLoaderToDisappear();
        return this;
    }

    @Step("Filter the directory by job title '{title}'")
    public void filterByJobTitle(String title) {

        waitForLoaderToDisappear();

        selectDropdownByLabel("Job Title", title);

        clickButtonByText("Search");

        waitForLoaderToDisappear();
    }

    @Step("Reset the directory filters")
    public void resetSearch() {
        clickButtonByText("Reset");
        waitForLoaderToDisappear();
    }

    @Step("Count the directory result cards")
    public int getCardCount() {
        return count(DIRECTORY_CARDS);
    }

    @Step("Confirm the directory returned at least one result")
    public boolean hasResults() {
        return WaitUtils.isVisible(DIRECTORY_CARDS);
    }

    @Step("Read the results-found summary text")
    public String getResultsText() {
        return getText(RESULTS_TEXT);
    }
}
