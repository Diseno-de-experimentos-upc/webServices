package step;

import com.example.digitalmindwebservices.entities.Certificate;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.entities.StudyCenter;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StudyCenterStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath = "http://localhost:%d/api/v1/study-centers";
    private String endpointPathGetEducationById = "http://localhost:%d/api/v1/educations";
    private ResponseEntity<String> responseEntity;
    private Education education;

    @Given("A developer with education id {string} wants to add a study center to his education")
    public void aDeveloperWithEducationIdWantsToAddAStudyCenterToHisEducation(String education_id) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
        this.endpointPathGetEducationById = String.format(endpointPathGetEducationById, randomServerPort);
        Map<String, String> params = new HashMap<>();
        params.put("education_id", education_id);
        education = testRestTemplate.getForObject(endpointPathGetEducationById+"/{education_id}", Education.class, params);
    }

    @When("A Study Center Request is sent with values {string}, {string}, {string}, {string}, {string}, {string}")
    public void aStudyCenterRequestIsSentWithValues(String description, String entryDate, String graduationDate, String iconUrl, String name, String progress) {
        Map<String, String> params = new HashMap<>();
        params.put("education_id", education.getId().toString());

        Integer progressInt = Integer.parseInt(progress);

        StudyCenter studyCenter = new StudyCenter(0L, name,iconUrl, progressInt,description, ParseDate(entryDate), ParseDate(graduationDate), education);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StudyCenter> request = new HttpEntity<>(studyCenter, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath+"/{education_id}", request, String.class, params);

    }
    public static Date ParseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try {
            result = format.parse(date);
        } catch (Exception ex){ };
        return result;
    }
    @Then("A Study Center with status code {int} is received")
    public void aStudyCenterWithStatusCodeIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }
}
