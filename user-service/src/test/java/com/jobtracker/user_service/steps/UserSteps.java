package com.jobtracker.user_service.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.jobtracker.user_service.utils.TestDataGenerator;
import java.util.Map;
import com.jobtracker.user_service.utils.JsonTemplateReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps {

    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response;

    @Given("the user API is available")
    public void the_user_api_is_available() {
        System.out.println("User API is available");
    }

    @Given("the user provides the correct credentials")
    public void the_user_provides_the_correct_credentials() {
        System.out.println("Using valid login credentials");
    }

    @When("I send a POST request to create a user")
    public void create_user() throws Exception {

        String email = TestDataGenerator.generateRandomEmail();

        String json = JsonTemplateReader.readAndReplace(
                "src/test/resources/testdata/createUser.json",
                Map.of("email", email));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("I send a GET request for user id {int}")
    public void get_user_by_id(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/users/" + id))
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("I send a POST request to Login")
    public void login_user() throws Exception {

        String json = """
                {
                    "email": "ali@test.com",
                    "password": "12345678"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/users/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("I send the POST request with invalid login")
    public void invalid_login() throws Exception {

        String json = """
                {
                    "email": "ali@test.com",
                    "password": "wrongpassword"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/users/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("the response status should be {int}")
    public void verify_status(int expectedStatus) {

        int actualStatus = response.statusCode();
        assertEquals(expectedStatus, actualStatus);
    }

    @Then("the response should contain user fields")
    public void the_response_should_contain_user_fields() {

        String body = response.body();

        if (!body.contains("name") || !body.contains("email")) {
            throw new AssertionError("User fields missing in response");
        }
    }
}