Feature: Company Service API 

Scenario: Create a new Company 
    Given the company API is available
    When I send a POST request to "/companies" with body:
    """
    {
        "compName":"RANDOM",
        "industry":"Business",
        "location":"Toronto",
        "website":"test.com"
    }
    """
    Then the response status should be 200


Scenario: Get company by Id
    Given the company API is available
    When I send a GET request to "/companies/42"
    Then the response status should be 200

Scenario: Get company by location
    Given the company API is available
    When I send a GET request to "/companies/location/Toronto"
    Then the response status should be 200

Scenario: Get company by industry
    Given the company API is available
    When I send a GET request to "/companies/industry/Technology"
    Then the response status should be 200

Scenario: Delete a company
    Given the company API is available
    When I send a POST request to "/companies" with body:
    """
    {
        "compName":"RANDOM",
        "industry":"Business",
        "location":"Toronto",
        "website":"test.com"
    }
    """
    And I send a DELETE request to the created company
    Then the response status should be 200

# Negative testing

Scenario: Create a new Company with missing fields
    Given the company API is available
    When I send a POST request to "/companies" with body:
    """
    {
        "compName":"PathSeekers"
    }
    """
    Then the response status should be 500

Scenario: Get company by id that does not exist
    Given the company API is available
    When I send a GET request to "/companies/1"
    Then the response status should be 500


Scenario: Get company by location that does not exist
    Given the company API is available
    When I send a GET request to "/companies/location/Manila"
    Then the response status should be 404


Scenario: Get company by industry that does not exist
    Given the company API is available
    When I send a GET request to "/companies/industry/Lending"
    Then the response status should be 404



# Test the analytics data


Scenario: Get location breakdown 
    Given the company API is available
    When I send a GET request to "/companies/group-by-location"
    Then the response status should be 200

Scenario: Get industry breakdown
    Given the company API is available
    When I send a GET request to "/companies/group-by-industry"
    Then the response status should be 200

Scenario: Get total companies count
    Given the company API is available
    When I send a GET request to "/companies/count-all"
    Then the response status should be 200