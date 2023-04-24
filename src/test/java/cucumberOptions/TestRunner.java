package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/feature"},
                 glue={"stepDefinition"},
        plugin = {"pretty", "json:target/cucumber-json-Reports/report.json",
                "html:target/cucumber-reports/report.html"})
public class TestRunner {
}
