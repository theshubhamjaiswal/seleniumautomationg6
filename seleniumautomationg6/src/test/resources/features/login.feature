@Login
Feature: Login Functionality


  @Smoke @Positive @Regression
  Scenario: Login with valid credentials
    When User enters username
    And User enters password
    And User clicks Login button
    Then User should navigate to inventory page

  @Negative @Regression
  Scenario: Login with invalid credentials
    When User enters invalid username
    And User enters invalid password
    And User clicks Login button
    Then Error message should be displayed
