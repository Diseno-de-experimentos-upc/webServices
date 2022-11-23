package step;

import com.example.digitalmindwebservices.entities.Message;
import com.example.digitalmindwebservices.entities.User;
import io.cucumber.java.en.And;
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
public class MessageStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath = "http://localhost:%d/api/v1/users";
    private ResponseEntity<String> responseEntity;
    private User emitter;
    private User receiver;


    @Given("A User recruiter with id {string} is chatting with User developer with id {string}")
    public void aUserRecruiterWithIdIsChattingWithUserDeveloperWithId(String id, String id2) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        emitter = testRestTemplate.getForObject(endpointPath+"/{id}", User.class, params);
        params.put("id2", id2);
        receiver = testRestTemplate.getForObject(endpointPath+"/{id2}", User.class, params);
    }

    @When("The User recruiter sends a message to User developer with content {string}")
    public void theUserRecruiterSendsAMessageToUserDeveloperWithContent(String content) {
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", emitter.getId());
        params.put("id2", receiver.getId());
        Message message = new Message(0L, content, emitter, receiver);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Message> request = new HttpEntity<>(message, headers);

        responseEntity = testRestTemplate.postForEntity(endpointPath+"/{id}/messages/{id2}", request, String.class, params);
    }
    @Then("the message is sent from recruiter to developer with status code {int}")
    public void theMessageIsSentFromRecruiterToDeveloperWithStatusCode(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }
}
