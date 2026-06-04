package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.PimPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class PimSteps {

    private final PimPage pimPage = new PimPage();

    @Then("at least {int} employee should be listed")
    public void at_least_employee_should_be_listed(int min) {
        Assert.assertTrue(pimPage.getResultRowCount() >= min,
                "Expected at least " + min + " employee row(s)");
    }

    @When("I search for an employee named {string}")
    public void i_search_for_an_employee_named(String name) {
        pimPage.searchEmployeeByName(name);
    }

    @Then("the employee results summary should be shown")
    public void the_employee_results_summary_should_be_shown() {
        Assert.assertTrue(pimPage.getResultsText().contains("Record"),
                "Employee results summary was not shown");
    }

    @When("I reset the employee search")
    public void i_reset_the_employee_search() {
        pimPage.resetSearch();
    }

    @When("I open the add employee form")
    public void i_open_the_add_employee_form() {
        pimPage.clickAddEmployee();
    }

    @When("I add an employee {string} {string}")
    public void i_add_an_employee(String firstName, String lastName) {
        pimPage.addEmployee(firstName, lastName);
    }

    @Then("the employee personal details page should be displayed")
    public void the_employee_personal_details_page_should_be_displayed() {
        Assert.assertTrue(pimPage.isOnPersonalDetails(),
                "Employee personal details page was not displayed");
    }
}
