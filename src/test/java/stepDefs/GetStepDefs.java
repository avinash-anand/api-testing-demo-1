package stepDefs;


import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import rest.RestResponseHolder;
import rest.SingletonRestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetStepDefs {

    private SingletonRestClient client;
    private String targetUri = "http://localhost:9000/registration/123456789";
    private RestResponseHolder response = new RestResponseHolder();


    @Given("^User has established GET connection$")
    public void user_has_established_GET_connection() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.client = SingletonRestClient.getInstance();
    }

    @When("^User requests for registration detail$")
    public void user_requests_for_registration_detail() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response.response = client.getClient().target(targetUri).request().get();
        response.responseBody = response.response.readEntity(String.class);
        response.responseStatus = response.response.getStatus();
        response.response.close();
    }

    @Then("^User should see \"([^\"]*)\" as Status code$")
    public void user_should_see_as_Status_code(String expectedStatusCode) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("expected was not equal to actual", Integer.parseInt(expectedStatusCode), response.responseStatus);
    }

    @Then("^User should receive following response body$")
    public void user_should_receive_following_response_body(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
        System.out.println(list);
        for(Map<String, String> map : arg1.asMaps(String.class, String.class)) {
            String key = map.get("key");
            String value = map.get("value");
        }
        System.out.print(response.responseBody);
    }

}
