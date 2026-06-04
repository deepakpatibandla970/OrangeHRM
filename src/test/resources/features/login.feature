@login
Feature: OrangeHRM authentication
  As a user of OrangeHRM
  I want to authenticate securely
  So that only valid users can access the application

  Background:
    Given I am on the login page

  @smoke @positive
  Scenario: Successful login with valid admin credentials
    When I log in with valid admin credentials
    Then I should land on the dashboard

  @negative
  Scenario Outline: Login fails with invalid credentials
    When I log in with username "<username>" and password "<password>"
    Then I should see the login error "Invalid credentials"

    Examples:
      | username  | password  |
      | Admin     | wrongpass |
      | wronguser | admin123  |
      | wronguser | wrongpass |

  @negative @validation
  Scenario: Required validation is shown when the username is empty
    When I log in with username "" and password "admin123"
    Then I should see a "Required" validation message

  @negative @validation
  Scenario: Required validation is shown when the password is empty
    When I log in with username "Admin" and password ""
    Then I should see a "Required" validation message

  @navigation
  Scenario: Forgot password link opens the reset password page
    When I click the forgot password link
    Then I should be on the reset password page

  @smoke
  Scenario: Logging out returns the user to the login page
    When I log in with valid admin credentials
    And I log out
    Then I should be back on the login page

