Feature: YourLogo login process
  Scenario:
    Given The home page is opened
    And The Sign in link is clicked

  Scenario:
    Given The Sign in link is clicked
    And The Create an account button is clicked with email filled
    Then Fill the required parameters
    Then Click the Register button

  Scenario:
    Given Check the user is logged in
    Then The Sign out button is clicked

  Scenario:
    Given The Sign In button is clicked
    Then An email address required error message is shown

  Scenario:
    Given The login form filled with wrong password
    Then The Sign In button is clicked
    Then An Authentication failed message is shown

  Scenario:
    Given The login form filled with good paramaters
    Then The Sign In button is clicked
    Then Check the user is logged in

  Scenario:
    Given Fill the search input with something
    Then The Search icon is clicked
    And Add the first element to cart

  Scenario:
    Given Close browser
