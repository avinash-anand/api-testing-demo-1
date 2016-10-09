package stepDefs;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import rest.RestResponseHolder;
import rest.SingletonRestClient;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GetStepDefs {

    private SingletonRestClient client;
    private String targetUri = "http://localhost:9000/registration/123456789";
    private RestResponseHolder response = new RestResponseHolder();


    @Given("^User has established GET connection$")
    public void user_has_established_GET_connection() throws Throwable {
        this.client = SingletonRestClient.getInstance();
    }

    @When("^User requests for registration detail$")
    public void user_requests_for_registration_detail() throws Throwable {
        response.response = client.getClient().target(targetUri).request().get();
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.json = new ObjectMapper().readTree(response.responseBody);
        response.response.close();
    }

    @Then("^User should see \"([^\"]*)\" as Status code$")
    public void user_should_see_as_Status_code(String expectedStatusCode) throws Throwable {
        Assert.assertEquals("expected was not equal to actual", Integer.parseInt(expectedStatusCode), response.responseStatus);
    }

    @Then("^User should receive following response body$")
    public void user_should_receive_following_response_body(DataTable arg1) throws Throwable {
//        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
//        System.out.println(list);
//        Iterator<java.util.Map.Entry<String, JsonNode>> it = response.json.getFields();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
        for (Map<String, String> map : arg1.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String value = map.get("value");
            assertEquals("expected was not equal to actual", response.json.get(key).asText(), value);
        }
//        System.out.print(response.responseBody);
    }

    @Then("^User should receive following response body key and types$")
    public void user_should_receive_following_response_body_key_and_types(DataTable dataTable) throws Throwable {
        for (Map<String, String> map : dataTable.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String value = map.get("type");
            assertEquals("expected was not equal to actual", response.json.get(key).asText(), value);
        }
    }


}
