
@tag
Feature: Purchase the order from E-commerce Website
  I want to use this template for my feature file

Background:
Given I landed on E-commerce page

  @Regression
  Scenario Outline: Positive test of the Submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmationPage

    Examples: 
      | name                     | password       | productName     |
      | pavankumar.h@gmail.com | Password@123 |ADIDAS ORIGINAL|
       
     