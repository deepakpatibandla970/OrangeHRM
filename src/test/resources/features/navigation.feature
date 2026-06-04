@navigation
Feature: Main menu navigation
  As an authenticated admin
  I want to open each module from the side menu
  So that I can confirm the core navigation works end to end

  Background:
    Given I am logged in as admin

  @smoke
  Scenario Outline: Navigate to the <module> module
    When I navigate to the "<module>" module
    Then the page URL should contain "<urlFragment>"

    Examples:
      | module      | urlFragment         |
      | Admin       | admin               |
      | PIM         | pim                 |
      | Leave       | leave               |
      | Time        | time                |
      | Recruitment | recruitment         |
      | My Info     | viewPersonalDetails |
      | Performance | performance         |
      | Directory   | directory           |
      | Maintenance | maintenance         |
      | Claim       | claim               |
      | Buzz        | buzz                |
      | Dashboard   | dashboard           |

