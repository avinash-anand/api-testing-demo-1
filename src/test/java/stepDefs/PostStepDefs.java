package stepDefs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class PostStepDefs {

    @Given("^User sends the following parameters as a \"([^\"]*)\" request$")
    public void user_sends_the_following_parameters_as_a_request(String methodType, DataTable data) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }

}
