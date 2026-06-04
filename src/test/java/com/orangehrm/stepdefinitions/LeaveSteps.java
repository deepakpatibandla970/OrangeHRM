package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.LeavePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LeaveSteps {

    private final LeavePage leavePage = new LeavePage();

    @When("I open the Leave {string} tab")
    public void i_open_the_leave_tab(String tab) {
        leavePage.openTab(tab);
    }

    @Then("the Apply Leave page should be displayed")
    public void the_apply_leave_page_should_be_displayed() {
        Assert.assertTrue(leavePage.isApplyLeavePageDisplayed(),
                "Apply Leave page was not displayed");
    }

    @Then("the leave type options should be available")
    public void the_leave_type_options_should_be_available() throws InterruptedException {
        Assert.assertFalse(leavePage.getLeaveTypeOptions().isEmpty(),
                "No leave type options were available");
    }

    @Then("a leave list should be displayed")
    public void a_leave_list_should_be_displayed() {
        Assert.assertTrue(leavePage.isLeaveListDisplayed(),
                "Leave list table was not displayed");
    }
}
