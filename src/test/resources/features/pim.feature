@pim
Feature: PIM - Employee management
  As an administrator
  I want to view, search and add employees
  So that I can keep personnel records up to date

  Background:
    Given I am logged in as admin
    And I navigate to the "PIM" module

  @smoke
  Scenario: Employee list loads with records
    Then at least 1 employee should be listed

  Scenario: Search for an employee by name
    When I search for an employee named "a"
    Then the employee results summary should be shown

  Scenario: Reset clears the PIM employee search
    When I search for an employee named "a"
    And I reset the employee search
    Then at least 1 employee should be listed

  @write
  Scenario: Add a new employee
    When I open the add employee form
    And I add an employee "Auto" "Tester"
    Then the employee personal details page should be displayed

