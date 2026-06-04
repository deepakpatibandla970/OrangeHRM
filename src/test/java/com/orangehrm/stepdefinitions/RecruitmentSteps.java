package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.RecruitmentPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.concurrent.atomic.AtomicReference;

public class RecruitmentSteps {

    private final RecruitmentPage recruitmentPage = new RecruitmentPage();

    @Then("at least {int} candidate should be listed")
    public void at_least_candidate_should_be_listed(int min) {
        Assert.assertTrue(recruitmentPage.getResultRowCount() >= min,
                "Expected at least " + min + " candidate row(s)");
    }

    @When("I filter candidates by status {string}")
    public void i_filter_candidates_by_status(String status) {
        recruitmentPage.filterByStatus(status);
    }

    @Then("the candidate results summary should be shown")
    public void the_candidate_results_summary_should_be_shown() {
        Assert.assertTrue(recruitmentPage.getResultsText().contains("Record"),
                "Candidate results summary was not shown");
    }

    @When("I click add candidate")
    public void i_click_add_candidate() {
        recruitmentPage.clickAdd();
    }

    @When("I add a candidate {string} {string} with email {string}")
    public void i_add_a_candidate_with_email(String firstName, String lastName, String email) {
        recruitmentPage.addCandidate(firstName, lastName, email);
    }


    @Then("a candidate success message should be shown")
    public void a_candidate_success_message_should_be_shown() {

        String message = recruitmentPage.getToastMessage();

        Assert.assertEquals(
                message,
                "Success\n" +
                        "Successfully Saved",
                "Candidate was not saved successfully"
        );
    }
}
