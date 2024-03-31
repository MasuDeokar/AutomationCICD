@tag
Feature: Purchase The Order From E-Commerce Website
  I want to use this template for my feature file

	Background: 
	Given I landed on Ecommerce Page
	@Regression
 @tag2
  Scenario Outline: Positive Test Of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from cart
    And CheckOut <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  										 | password 				| productName |
      | mayureshdeokar@gmail.com	 |     Shamanth@123 | ZARA COAT 3	| 