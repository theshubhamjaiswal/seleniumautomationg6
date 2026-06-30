package hooks_cucumber;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base_cucumber.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MyHooks extends BaseTest {
	@Before
	public void beforeScenario() {
		setUp();
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			String ssname = scenario.getName().replaceAll(" ", "_");
			TakesScreenshot tks = (TakesScreenshot) driver;
			byte[] ss = tks.getScreenshotAs(OutputType.BYTES);
			scenario.attach(ss, "image/png", ssname);
			
		}

		tearDown();
	}
}
