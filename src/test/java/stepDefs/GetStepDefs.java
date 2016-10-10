package stepDefs;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import rest.RestResponseHolder;
import rest.SingletonRestClient;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.TypeUtils.isOfType;

public class GetStepDefs {

    private String targetUri = "http://localhost:9000/registration/123456789";
    private RestResponseHolder response = new RestResponseHolder();

    @Given("^User requests for registration detail$")
    public void user_requests_for_registration_detail() throws Throwable {
        SingletonRestClient client = SingletonRestClient.getInstance();
        response.response = client.getClient().target(targetUri).request().get();
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        response.response.close();
    }

    @Then("^User should receive \"([^\"]*)\" as Status code$")
    public void user_should_receive_as_Status_code(String expectedStatusCode) throws Throwable {
        Assert.assertEquals("expected was not equal to actual", Integer.parseInt(expectedStatusCode), response.responseStatus);
    }

    @Then("^User should receive following response body$")
    public void user_should_receive_following_response_body(DataTable arg1) throws Throwable {
        for (Map<String, String> map : arg1.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String value = map.get("value");
            assertEquals("expected was not equal to actual", response.json.get(key).asText(), value);
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
