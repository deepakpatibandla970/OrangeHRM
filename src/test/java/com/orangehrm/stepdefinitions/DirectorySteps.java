package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.DirectoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DirectorySteps {

    private final DirectoryPage directoryPage = new DirectoryPage();

    @Then("at least {int} directory result should be shown")
    public void at_least_directory_result_should_be_shown(int min) {
        Assert.assertTrue(directoryPage.getCardCount() >= min,
                "Expected at least " + min + " directory result card(s)");
    }

    @When("I filter the directory by job title {string}")
    public void i_filter_the_directory_by_job_title(String title) {
        directoryPage.filterByJobTitle(title);
    }

    @Then("the directory results summary should be shown")
    public void the_directory_results_summary_should_be_shown() {
        Assert.assertTrue(directoryPage.getResultsText().contains("Record"),
                "Directory results summary was not shown");
    }

    @When("I reset the directory filters")
    public void i_reset_the_directory_filters() {
        directoryPage.resetSearch();
    }
}
