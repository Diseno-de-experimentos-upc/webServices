package step;

import com.example.digitalmindwebservices.entities.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint to add user {string} is available")
    public void theEndpointToAddUserIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A User Request is sent with values {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void aUserRequestIsSentWithValues(String firstName, String lastName, String email, String phone, String password, String role, String description, String image, String bannerImage) {
        User user = new User(0L, firstName, lastName, email, phone, password, role, description, image, bannerImage);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A User with status {int} is received")
    public void aUserWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }


    @When("A User Delete is sent with id {string}")
    public void aUserDeleteIsSentWithId(String user_id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", user_id);
        testRestTemplate.delete(endpointPath+"/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A User Selected is sent with id {string}")
    public void aUserSelectedIsSentWithId(String user_id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", user_id);
        User user = testRestTemplate.getForObject(endpointPath+"/{id}", User.class, params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
        System.out.println(user.toString());
    }

    @When("A User who are registered in DB")
    public void aUserWhoAreRegisteredInDB() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        responseEntity = testRestTemplate.exchange(endpointPath, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity);
    }

    @Then("A List of Customer with status {int} is received")
    public void aListOfCustomerWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }



    



}
