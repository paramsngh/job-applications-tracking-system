Feature: Job Application API 

Scenario: Create a job Application

    When I send a POST request to "/applications"
    Then the response status should be 200


Scenario: Get job application by id
    When I send a GET request to "/applications/1"
    Then the response status should be 200

Scenario: Delete a job application using id
    When I send a DELETE request to "/applications/61"
    Then the response status should be 200

Scenario: Get company information for an application
    When I send a GET request to "/applications/1/company"
    Then the response status should be 200

Scenario: Get company information for an application
    When I send a GET request to "/applications/1/company"
    Then the response status should be 200
    And the response should contain "compName"