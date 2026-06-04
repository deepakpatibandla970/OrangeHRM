package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class NavigationSteps {

    @Given("I am logged in as admin")
    public void i_am_logged_in_as_admin() {
        DashboardPage dashboard = new LoginPage().open().loginAsAdmin();
        Assert.assertTrue(dashboard.isLoaded(), "Could not log in as admin");
    }

    @When("I navigate to the {string} module")
    public void i_navigate_to_the_module(String module) {
        new DashboardPage().navigateTo(module);
    }

    @Then("the page URL should contain {string}")
    public void the_page_url_should_contain(String fragment) {
        Assert.assertTrue(new DashboardPage().isOnUrl(fragment),
                "URL did not contain '" + fragment + "'");
    }
}
