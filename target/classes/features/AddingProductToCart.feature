Feature: As a user
  i want to add product to cart

  Scenario: Add a product to the cart
    Given Open the site "https://www.onliner.by/"
    When I search for a product "iphone"
    And I select the first search result
    And I add the product to the cart clicking on the button 'В корзину'
    Then the product should be added to the cart