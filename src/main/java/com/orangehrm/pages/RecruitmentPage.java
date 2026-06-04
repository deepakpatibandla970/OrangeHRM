package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecruitmentPage extends BasePage {



    private static final By FIRST_NAME =
            By.cssSelector("input[name='firstName']");

    private static final By LAST_NAME =
            By.cssSelector("input[name='lastName']");

    private static final By RESULTS_TEXT =
            By.xpath("//span[contains(@class,'oxd-text--span') and contains(.,'Record')]");

    private static final By TABLE_ROWS =
            By.cssSelector(".oxd-table-body .oxd-table-card");

    private static final By TOAST =
            By.xpath("//div[contains(@class,'oxd-toast-content')]");

    private static final By ADD_CANDIDATE_HEADER =
            By.xpath("//h6[normalize-space()='Add Candidate']");

    private String toastMessage;

    private static By topNavTab(String tab) {
        return By.xpath(
                "//a[contains(@class,'oxd-topbar-body-nav-tab-item') "
                        + "and normalize-space()='" + tab + "']"
        );
    }

    @Step("Verify Recruitment page is loaded")
    public boolean isLoaded() {
        return WaitUtils.urlContains("recruitment");
    }

    @Step("Open the Recruitment sub-tab '{tab}'")
    public RecruitmentPage openTab(String tab) {

        click(topNavTab(tab));

        waitForLoaderToDisappear();

        return this;
    }

    @Step("Open the Add Candidate form")
    public void clickAdd() {

        driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();

        waitForLoaderToDisappear();

    }

    @Step("Confirm the Add Candidate form is displayed")
    public boolean isAddCandidateFormDisplayed() {

        return isDisplayed(ADD_CANDIDATE_HEADER);
    }

    @Step("Add candidate {firstName} {lastName} ({email})")
    public void addCandidate(
            String firstName,
            String lastName,
            String email
    ) {

        type(FIRST_NAME, firstName);

        type(LAST_NAME, lastName);

        typeInFieldByLabel("Email", email);

        waitForLoaderToDisappear();

        clickButtonByText("Save");

// Read toast immediately before it disappears
        // Read toast immediately before it disappears
    }

    @Step("Filter candidates by status '{status}'")
    public void filterByStatus(String status) {

        waitForLoaderToDisappear();

        selectDropdownByLabel("Status", status);

        clickButtonByText("Search");

        waitForLoaderToDisappear();

    }

    @Step("Read the results-found summary text")
    public String getResultsText() {

        waitForLoaderToDisappear();

        return getText(RESULTS_TEXT);
    }

    @Step("Count the candidate rows")
    public int getResultRowCount() {

        waitForLoaderToDisappear();

        return count(TABLE_ROWS);
    }

    /*@Step(value = "Read the confirmation toast message")
    public String getToastMessage()

    {

        By toast =
                By.xpath("//p[text()='Successfully Saved']");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.pollingEvery(Duration.ofMillis(200));

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(toast)
        ).getText().trim();
    }
    public String getStoredToastMessage() {
        return toastMessage;
    }*/
}