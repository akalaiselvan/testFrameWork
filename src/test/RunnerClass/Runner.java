package RunnerClass;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/feature",glue = "stepDefinition"
        ,plugin = {"pretty","html:Reports/html","json:Reports/json/cu.json","junit:Reports/xml/cu.xml"})
public class Runner {
}
