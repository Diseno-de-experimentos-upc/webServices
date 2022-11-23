package step;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.ProgrammingLanguage;
import com.example.digitalmindwebservices.entities.Project;
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
import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammingLanguageStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath = "http://localhost:%d/api/v1/programmingLanguages";
    private String endpointPathGetDigitalProfileById = "http://localhost:%d/api/v1/digital_profiles";
    private ResponseEntity<String> responseEntity;
    private DigitalProfile digitalProfile;
    @Given("A developer with digital profile id {string} wants to add a programming language to his digital profile")
    public void aDeveloperWithDigitalProfileIdWantsToAddAProgrammingLanguageToHisDigitalProfile(String digitalProfile_id) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
        this.endpointPathGetDigitalProfileById = String.format(endpointPathGetDigitalProfileById, randomServerPort);
        Map<String, String> params = new HashMap<>();
        params.put("digitalProfile_id", digitalProfile_id);
        digitalProfile = testRestTemplate.getForObject(endpointPathGetDigitalProfileById+"/{digitalProfile_id}", DigitalProfile.class, params);
    }

    @When("A Programming Language Request is sent with values {string}, {string},{string}")
    public void aProgrammingLanguageRequestIsSentWithValues(String description, String iconLink, String name) {
        Map<String, String> params = new HashMap<>();
        params.put("digitalProfile_id", digitalProfile.getId().toString());

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(0L, description, iconLink, name, digitalProfile);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProgrammingLanguage> request = new HttpEntity<>(programmingLanguage, headers);

        responseEntity = testRestTemplate.postForEntity(endpointPath+"/{digitalProfile_id}", request, String.class, params);

    }

    @Then("A Programming Language with status code {int} is received")
    public void aProgrammingLanguageWithStatusCodeIsReceived(int expectedStatusCode ) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }


}
