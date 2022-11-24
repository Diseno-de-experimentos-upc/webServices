package step;

import com.example.digitalmindwebservices.entities.Developer;
import com.example.digitalmindwebservices.entities.DigitalProfile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
//import assertThat
import static org.assertj.core.api.Assertions.assertThat;

public class DigitalProfileStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;
    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A Digital Profile Request is sent with values {string}, {string}")
    public void aDigitalProfileRequestIsSentWithValues(String developer_id, String name) {
        Map<String, String> params = new HashMap<>();
        params.put("developer_id", developer_id);
        //convert developer_id to Long
        Long developerId = Long.parseLong(developer_id);

        Developer developer = new Developer(developerId, "John", "Doe", "email", "phone", "1234", "developer", "im a developer", "url to image", "url to banner");
        DigitalProfile digitalProfile = new DigitalProfile(0L, name, developer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DigitalProfile> request = new HttpEntity<>(digitalProfile, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath+"/{developer_id}", request, String.class, params);

    }

    @Then("A Digital Profile with status code {int} is received")
    public void aDigitalProfileWithStatusCodeIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }
}
