Feature: End-to-End Checkout Flow

  Scenario: Successful login and checkout
    Given User opens the browser and navigates to the login page
    When User logs in with username "standard_user" and password "secret_sauce"
    And User adds backpack item to cart
    And User navigates to the cart page
    And User completes the checkout process
    Then Checkout should be successful
