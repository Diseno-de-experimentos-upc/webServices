package step;

import com.example.digitalmindwebservices.entities.Notification;
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

import static org.assertj.core.api.Assertions.assertThat;
public class NotificationStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath = "http://localhost:%d/api/v1/users";
    private ResponseEntity<String> responseEntity;
    private User emitter;
    private User receiver;

    @Given("the User developer with id {string} is in notifications section")
    public void theUserDeveloperWithIdIsInNotificationsSection(String id) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        emitter = testRestTemplate.getForObject(endpointPath+"/{id}", User.class, params);
    }

    @When("the User developer clicks on the delete button in the notification with id {string} from recruiter with id {string}")
    public void theUserDeveloperClicksOnTheDeleteButtonInTheNotificationWithIdFromRecruiterWithId(String id, String recruiterid) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("userid", emitter.getId().toString());
        params.put("recruiterid", recruiterid);
        receiver = testRestTemplate.getForObject(endpointPath+"/{recruiterid}", User.class, params);
        testRestTemplate.delete(endpointPath+"/{userid}/notifications/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @Then("the notification is deleted with success code {int}")
    public void theNotificationIsDeletedWithSuccessCode(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }
}
