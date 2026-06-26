@Inventory
Feature: Inventory Page Functionality

  Background:
    Given User is logged into application

  @Smoke @Regression
  Scenario: Verify inventory page title
    Then Inventory page title should be Products

  @Regression
  Scenario: Verify products are displayed
    Then Product list should be displayed

  @Regression
  Scenario: Verify cart icon is visible
    Then Cart icon should be displayed
