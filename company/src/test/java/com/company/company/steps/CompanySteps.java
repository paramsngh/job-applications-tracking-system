package com.company.company.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.company.company.utils.CompanyNameGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanySteps {

    @LocalServerPort
    int port;

    Long createdCompanyId;
    ObjectMapper objectMapper = new ObjectMapper();
    HttpResponse<String> response;

    private String baseUrl(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    @Given("the company API is available")
    public void the_company_api_is_available() {
        System.out.println("Running on port: " + port);
    }

    @When("I send a POST request to {string} with body:")
    public void sendPostRequest(String endpoint, String jsonBody) throws Exception {

        jsonBody = jsonBody.replace("RANDOM", CompanyNameGenerator.generateRandomName());

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl(endpoint)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Map<String, Object> json = objectMapper.readValue(response.body(), Map.class);
            createdCompanyId = ((Number) json.get("compId")).longValue();
        }

        System.out.println("Created body: " + response.body());
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        assertEquals(status, response.statusCode());
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl(endpoint)))
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @When("I send a DELETE request to the created company")
    public void sendDeleteRequest() throws Exception {

        if (createdCompanyId == null) {
            throw new RuntimeException("createdCompanyId is null — POST failed");
        }

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/companies/" + createdCompanyId))
                .DELETE()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\n=== DELETE RESPONSE ===");
        System.out.println(response.body());
        System.out.println("========================\n");
    }
}