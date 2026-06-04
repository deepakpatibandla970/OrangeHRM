@leave
Feature: Leave module
  As an authenticated admin
  I want to access leave application and lists
  So that I can manage employee leave

  Background:
    Given I am logged in as admin
    And I navigate to the "Leave" module

  Scenario: Open the Apply Leave page
    When I open the Leave "Apply" tab
    Then the Apply Leave page should be displayed

  Scenario: The Apply Leave page lists leave types
    When I open the Leave "My Leave" tab
    Then the leave type options should be available

  Scenario: My Leave page shows a leave list
    When I open the Leave "My Leave" tab
    Then a leave list should be displayed

