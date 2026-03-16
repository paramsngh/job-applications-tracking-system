Feature: User Service API

Scenario: Create a new User
    Given the user API is available
    When I send a POST request to create a user 
    Then the response status should be 200

Scenario: Login with correct credentials
    Given the user provides the correct credentials
    When I send a POST request to Login
    Then the response status should be 200

Scenario: Login with wrong password
    Given the user API is available
    When I send the POST request with invalid login
    Then the response status should be 500

Scenario: Get user by id
  Given the user API is available
  When I send a GET request for user id 42
  Then the response status should be 200

Scenario: Get user with invalid id
  Given the user API is available
  When I send a GET request for user id 999
  Then the response status should be 500

Scenario: Verify user response structure
  Given the user API is available
  When I send a GET request for user id 21
  Then the response should contain user fields