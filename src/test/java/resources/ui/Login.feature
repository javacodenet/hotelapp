Feature: Login

  Scenario: Should be able to login with valid credentials
    Given User enters "j" as username and "p" as password
    When  he clicks submit
    Then  login successful page should be displayed
