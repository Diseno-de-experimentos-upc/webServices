package step;

import com.example.digitalmindwebservices.entities.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateUserStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint to update user {string} is available")
    public void theEndpointToUpdateUserIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }
    @When("A User update is sent with id {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void aUserUpdateIsSentWithIdId(String user_id, String firstName, String lastName, String email, String phone, String password, String role, String description, String image, String bannerImage) {
        Map<String, String> params = new HashMap<>();
        params.put("id", user_id);
        User user = new User(0L, firstName, lastName, email, phone, password, role, description, image, bannerImage);
        testRestTemplate.put(endpointPath+"/{id}", user, params);
        responseEntity = new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @Then("A User update with status {int} is received")
    public void aUserUpdateWithStatusIsReceived(int expectedCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(actualStatusCode).isEqualTo(expectedCode);
    }
}
