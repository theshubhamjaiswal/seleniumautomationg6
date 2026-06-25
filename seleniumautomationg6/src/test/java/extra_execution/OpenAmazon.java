package extra_execution;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenAmazon {
	@Test(retryAnalyzer = listener_utility.ListRetryImp.class)
	public void opening() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.amazon.com/");
		driver.quit();
		Assert.assertTrue(false);
	}

}
