@Cart
Feature: Cart Functionality

  Background:
    Given User is logged into application

  @Smoke @Regression
  Scenario: Add product to cart
    When User clicks Add To Cart button
    Then Cart badge count should be "1"

  @Regression
  Scenario: Open cart page
    When User clicks cart icon
    Then User should navigate to cart page

  @Regression @Positive
  Scenario: Verify added product in cart
    Given Product is added into cart
    When User clicks cart icon
    Then Product should be visible in cart