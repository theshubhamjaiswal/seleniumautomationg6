@Logout
Feature: Logout Functionality

  Background:
    Given User is logged into application

  @Smoke @Regression
  Scenario: Successful logout
    When User clicks menu button
    And User clicks logout link
    Then User should navigate to login page
