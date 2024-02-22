
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on E-commerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed in confirmationPage

    Examples: 
      | name                     | password       | 
      | pavankumar.h@gmail.com | Password@13 |
