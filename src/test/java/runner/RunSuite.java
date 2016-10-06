package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(value = Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"stepDefs"},
        tags = {"@suite", "~@wip", "~@ignore"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        strict =true
)
public class RunSuite {

}
