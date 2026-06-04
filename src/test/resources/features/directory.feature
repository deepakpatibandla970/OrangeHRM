@directory
Feature: Directory module
  As an authenticated admin
  I want to browse and filter the employee directory
  So that I can find colleagues quickly

  Background:
    Given I am logged in as admin
    And I navigate to the "Directory" module

  @smoke
  Scenario: Directory shows employee cards
    Then at least 1 directory result should be shown

  Scenario: Filter the directory by job title
    When I filter the directory by job title "Chief Executive Officer"
    Then the directory results summary should be shown

  Scenario: Reset clears the directory filters
    When I filter the directory by job title "Chief Executive Officer"
    And I reset the directory filters
    Then at least 1 directory result should be shown

