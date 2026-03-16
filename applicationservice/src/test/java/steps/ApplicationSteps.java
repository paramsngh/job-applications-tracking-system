package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import utils.CompanyIdGenerator;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationSteps {

    HttpResponse<String> response;

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) throws Exception {

        int companyId = CompanyIdGenerator.generateCompanyId();

        String jsonBody = """
                {
                  "userId":35,
                  "companyId":%d,
                  "position":"AI Engineer",
                  "status":"Applied",
                  "dateApplied":"2026-03-07",
                  "notes":"Applied through LinkedIn"
                }
                """.formatted(companyId);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        assertEquals(status, response.statusCode());
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + endpoint))
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

    @When("I send a DELETE request to {string}")
    public void sendDeleteRequest(String endpoint) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + endpoint))
                .DELETE()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @Then("the response should contain {string}")
    public void verifyResponseContains(String field) {
        assertTrue(response.body().contains(field));
    }

}