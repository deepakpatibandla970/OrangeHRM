@recruitment
Feature: Recruitment module
  As an administrator
  I want to view, filter and add candidates
  So that I can manage the hiring pipeline

  Background:
    Given I am logged in as admin
    And I navigate to the "Recruitment" module

  @smoke
  Scenario: Candidates list loads
    Then at least 1 candidate should be listed

  Scenario: Filter candidates by status
    When I filter candidates by status "Application Initiated"
    Then the candidate results summary should be shown

  @write
  Scenario: Add a new candidate
    When I click add candidate
    And I add a candidate "Auto" "Candidate" with email "auto.candidate@example.com"
    Then a candidate success message should be shown

