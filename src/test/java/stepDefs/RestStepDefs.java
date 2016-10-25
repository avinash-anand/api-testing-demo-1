package stepDefs;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.Registration;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import rest.RestResponseHolder;
import rest.SingletonRestClient;

import javax.ws.rs.client.Entity;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.TypeUtils.isOfType;

public class RestStepDefs {

    private String getTargetUri = "http://localhost:9000/registration/123456789";
    private String postTargetUri = "http://localhost:9000/registration";
    private String putTargetUri = "http://localhost:9000/registration";
    private String deleteTargetUri = "http://localhost:9000/registration/987654321";
    private RestResponseHolder response = new RestResponseHolder();

    // GET
    @Given("^User requests for registration detail$")
    public void user_requests_for_registration_detail() throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        response.response = client.getClient().target(getTargetUri).request().get();
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        response.response.close();
    }

    // POST
    @Given("^User sends the following parameters as a registration object as a POST request$")
    public void user_sends_the_following_parameters_as_a_post_request(DataTable data) throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        Registration model = new Registration();
        Map<String, String> dataForModel = data.asMap(String.class, String.class);
        model.registrationId = Integer.parseInt(dataForModel.get("registrationId"));
        model.cost = Integer.parseInt(dataForModel.get("registrationId"));
        model.isActive = Boolean.parseBoolean(dataForModel.get("registrationId"));
        model.processingDate = new DateTime(dataForModel.get("registrationId"));
        model.registrationDate = new LocalDate(dataForModel.get("registrationId"));
        model.status = dataForModel.get("registrationId");
        response.response = client.getClient().target(postTargetUri).request().post(Entity.entity(model, "application/json"));
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        response.response.close();
    }

    // POST
    @Given("^User sends the following parameters as a registration object as a PUT request$")
    public void user_sends_the_following_parameters_as_a_put_request(DataTable data) throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        Registration model = new Registration();
        Map<String, String> dataForModel = data.asMap(String.class, String.class);
        model.registrationId = Integer.parseInt(dataForModel.get("registrationId"));
        model.cost = Integer.parseInt(dataForModel.get("registrationId"));
        model.isActive = Boolean.parseBoolean(dataForModel.get("registrationId"));
        model.processingDate = new DateTime(dataForModel.get("registrationId"));
        model.registrationDate = new LocalDate(dataForModel.get("registrationId"));
        model.status = dataForModel.get("registrationId");
        response.response = client.getClient().target(putTargetUri).request().put(Entity.entity(model, "application/json"));
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        response.response.close();
    }

    // GET
    @Given("^User wants to delete registration by id$")
    public void user_wants_to_delete_registration_by_id() throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        response.response = client.getClient().target(deleteTargetUri).request().delete();
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        System.out.println("json = " + response.json);
        response.response.close();
    }

    @Then("^User should receive \"([^\"]*)\" as Status code$")
    public void user_should_receive_as_Status_code(String expectedStatusCode) throws Throwable {
        assertEquals("expected was not equal to actual", Integer.parseInt(expectedStatusCode), response.responseStatus);
    }

    @Then("^User should receive following response body$")
    public void user_should_receive_following_response_body(DataTable arg1) throws Throwable {
        for (Map<String, String> map : arg1.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String value = map.get("value");
            assertEquals("expected was not equal to actual", value, response.json.get(key).asText());
        }
    }

    @Then("^User should receive following response body key and types$")
    public void user_should_receive_following_response_body_key_and_types(DataTable dataTable) throws Throwable {
        for (Map<String, String> map : dataTable.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String type = map.get("type").toLowerCase();
            JsonNode node = response.json.get(key);
            assertTrue("expected type - " + type + " - was not equal to actual - node - " + node, isOfType(node, type));
        }
    }


}
