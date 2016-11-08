package stepDefs;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import models.Registration;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.RestResponseHolder;
import rest.SingletonRestClient;
import utils.PropertyFileReader;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.HeaderUtils.addHeaders;
import static utils.HttpMethodType.*;
import static utils.TypeUtils.isOfType;

public class RestStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(RestStepDefs.class);

    private RestResponseHolder response = new RestResponseHolder();

    // GET
    @Given("^User requests for registration detail for id \"([^\"]*)\"$")
    public void user_requests_for_registration_detail(String getId) throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        String getTargetUri = createTargetUri("api.get.uri") + "/" + getId;
        storeResponse(addHeaders(client.getClient().target(getTargetUri).request(), GET).get());
    }

    // POST
    @Given("^User sends the following parameters as a registration object as a POST request$")
    public void user_sends_the_following_parameters_as_a_post_request(DataTable data) throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        Registration model = createRegistrationFromDataTable(data);
        String postTargetUri = createTargetUri("api.post.uri");
        storeResponse(addHeaders(client.getClient().target(postTargetUri).request(), POST).post(Entity.entity(model, "application/json")));
    }

    // PUT
    @Given("^User sends the following parameters as a registration object as a PUT request$")
    public void user_sends_the_following_parameters_as_a_put_request(DataTable data) throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        Registration model = createRegistrationFromDataTable(data);
        String putTargetUri = createTargetUri("api.put.uri");
        storeResponse(addHeaders(client.getClient().target(putTargetUri).request(), PUT).put(Entity.entity(model, "application/json")));
    }

    // DELETE
    @Given("^User wants to delete registration by id \"([^\"]*)\"$")
    public void user_wants_to_delete_registration_by_id(String deleteId) throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        String deleteTargetUri = createTargetUri("api.delete.uri") + "/" + deleteId;
        storeResponse(addHeaders(client.getClient().target(deleteTargetUri).request(), DELETE).delete());
        logger.debug("json = {}", response.json);
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
            JsonNode node = response.json;
            String[] keyNodes = key.split("\\.");
            for(String keyNode: keyNodes) {
                node = node.get(keyNode);
            }
            assertEquals("expected was not equal to actual", value, node.asText());
        }
    }

    @Then("^User should receive following response body key and types$")
    public void user_should_receive_following_response_body_key_and_types(DataTable dataTable) throws Throwable {
        for (Map<String, String> map : dataTable.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String type = map.get("type").toLowerCase();
            JsonNode node = response.json;
            for(String keyNode: key.split("\\.")) {
                node = node.get(keyNode);
            }
            assertTrue("expected type - " + type + " - was not equal to actual - node - " + node, isOfType(node, type));
        }
    }

    private Registration createRegistrationFromDataTable(DataTable dataTable) {
        Registration model = new Registration();
        Map<String, String> dataForModel = dataTable.asMap(String.class, String.class);
        model.registrationId = Integer.parseInt(dataForModel.get("registrationId"));
        model.status = dataForModel.get("status");
        model.cost = Double.parseDouble(dataForModel.get("cost"));
        model.isActive = Boolean.parseBoolean(dataForModel.get("isActive"));
        model.processingDate = new DateTime(dataForModel.get("processingDate"));
        model.registrationDate = new LocalDate(dataForModel.get("registrationDate"));
        return model;
    }

    private String createTargetUri(String requestTypeParam) {
        String environment = System.getProperty("environment", "dev");
        String baseUriParam = environment + ".api.base.uri";
        System.out.println("baseUriParam = " + baseUriParam);
        String baseUri = PropertyFileReader.getProperty(baseUriParam);
        return baseUri + PropertyFileReader.getProperty(requestTypeParam);
    }

    private void storeResponse(Response resp) throws Throwable {
        response.response = resp;
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        response.response.close();
    }


}
