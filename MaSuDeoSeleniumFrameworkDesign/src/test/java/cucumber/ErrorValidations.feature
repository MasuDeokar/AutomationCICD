@tag
Feature: Error validation
  I want to use this template for my feature file
@ErrorValidation
  @tag2
  Scenario Outline: Negative Test Of The Login
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
 
    Examples: 
      | name  										     | password 				   | 
      | mayureshdeokar@gmail123.com	   | Shamanth@123        |
