@dashboard
Feature: Dashboard overview
  As an authenticated admin
  I want the dashboard to surface widgets and account options
  So that I can navigate and manage my session

  Background:
    Given I am logged in as admin

  @smoke
  Scenario: Dashboard displays at least one widget
    Then I should see at least 1 dashboard widget

  Scenario: Dashboard exposes quick launch shortcuts
    Then I should see at least 1 quick launch shortcut

  Scenario: User account menu lists session options
    When I open the user account menu
    Then the account menu should contain "Logout"
    And the account menu should contain "About"

