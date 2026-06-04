package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.DashboardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class DashboardSteps {

    private final DashboardPage dashboard = new DashboardPage();
    private List<String> menuOptions;

    @When("I log out")
    public void i_log_out() {
        dashboard.logout();
    }

    @Then("I should see at least {int} dashboard widget")
    public void i_should_see_at_least_dashboard_widget(int min) {
        Assert.assertTrue(dashboard.getWidgetCount() >= min,
                "Expected at least " + min + " dashboard widget(s)");
    }

    @Then("I should see at least {int} quick launch shortcut")
    public void i_should_see_at_least_quick_launch_shortcut(int min) {
        Assert.assertTrue(dashboard.getQuickLaunchCount() >= min,
                "Expected at least " + min + " quick launch shortcut(s)");
    }

    @When("I open the user account menu")
    public void i_open_the_user_account_menu() {
        menuOptions = dashboard.getUserDropdownOptions();
    }

    @Then("the account menu should contain {string}")
    public void the_account_menu_should_contain(String option) {
        Assert.assertTrue(menuOptions.contains(option),
                "Account menu did not contain '" + option + "'. Found: " + menuOptions);
    }
}
