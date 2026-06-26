package runner_cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
			features="src/test/resources/features",
			glue= {"step_definition","hooks"},
			plugin= {
					"pretty",
					"html:target/cucumberhtml.html",
					"json:target/cucumberjson.json"
			},
		tags="@negative"
	)
public class TestRunner extends AbstractTestNGCucumberTests {}
