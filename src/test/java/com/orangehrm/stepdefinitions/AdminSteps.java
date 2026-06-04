package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.AdminPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class AdminSteps {

    private final AdminPage adminPage = new AdminPage();

    @When("I search for the user {string}")
    public void i_search_for_the_user(String username) {
        adminPage.searchUser(username);
    }

    @Then("the results table should contain {string}")
    public void the_results_table_should_contain(String value) {
        Assert.assertTrue(adminPage.tableContains(value),
                "Results table did not contain '" + value + "'");
    }

    @Then("the User Role filter should include {string} and {string}")
    public void the_user_role_filter_should_include_and(String first, String second) throws InterruptedException {
        List<String> options = adminPage.getUserRoleOptions();
        Assert.assertTrue(options.contains(first) && options.contains(second),
                "User Role options missing expected values. Found: " + options);
    }

    @When("I filter users by role {string}")
    public void i_filter_users_by_role(String role) {
        adminPage.filterByRole(role);
    }

    @When("I reset the search")
    public void i_reset_the_search() {
        adminPage.resetSearch();
    }

    @Then("at least {int} user should be listed")
    public void at_least_user_should_be_listed(int min) {
        Assert.assertTrue(adminPage.getResultRowCount() >= min,
                "Expected at least " + min + " user row(s)");
    }
}
