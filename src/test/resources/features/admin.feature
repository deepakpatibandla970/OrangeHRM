@admin
Feature: Admin - System Users
  As an administrator
  I want to search and filter system users
  So that I can manage access to OrangeHRM

  Background:
    Given I am logged in as admin
    And I navigate to the "Admin" module

  @smoke
  Scenario: Search a system user by username
    When I search for the user "Admin"
    Then the results table should contain "Admin"

  Scenario: User Role filter offers the expected roles
    Then the User Role filter should include "Admin" and "ESS"

  Scenario: Filter system users by the Admin role
    When I filter users by role "Admin"
    Then at least 1 user should be listed

  Scenario: Reset clears the Admin search filters
    When I search for the user "NoSuchUser12345"
    And I reset the search
    Then at least 1 user should be listed

