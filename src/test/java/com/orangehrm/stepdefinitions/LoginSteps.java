package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.ForgotPasswordPage;
import com.orangehrm.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage = new LoginPage().open();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page did not load");
    }

    @When("I log in with valid admin credentials")
    public void i_log_in_with_valid_admin_credentials() {
        dashboardPage = loginPage.loginAsAdmin();
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should land on the dashboard")
    public void i_should_land_on_the_dashboard() {
        DashboardPage dashboard = dashboardPage != null ? dashboardPage : new DashboardPage();
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard was not displayed after login");
    }

    @Then("I should see the login error {string}")
    public void i_should_see_the_login_error(String expected) {
        Assert.assertTrue(loginPage.isAlertDisplayed(), "No login alert was shown");
        Assert.assertEquals(loginPage.getAlertMessage(), expected,
                "Unexpected login error message");
    }

    @Then("I should see a {string} validation message")
    public void i_should_see_a_validation_message(String expected) {
        Assert.assertTrue(loginPage.getFieldErrorMessages().contains(expected),
                "Expected a '" + expected + "' validation message but none was found");
    }

    @When("I click the forgot password link")
    public void i_click_the_forgot_password_link() {
        loginPage.clickForgotPassword();

    }

    @Then("I should be on the reset password page")
    public void i_should_be_on_the_reset_password_page() {
        Assert.assertTrue(new ForgotPasswordPage().isResetPasswordPageDisplayed(),
                "Reset password page is displayed");
    }

    @Then("I should be back on the login page")
    public void i_should_be_back_on_the_login_page() {
        Assert.assertTrue(new LoginPage().isLoginPageDisplayed(),
                "User was not returned to the login page");
    }
}
