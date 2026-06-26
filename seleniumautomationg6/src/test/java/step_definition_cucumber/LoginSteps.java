package step_definition_cucumber;

import org.openqa.selenium.By;
import org.testng.Assert;

import base_cucumber.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest {
	@When("User enters username")
	public void user_enters_username() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");

	}

	@When("User enters password")
	public void user_enters_password() {
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
	}

	@When("User clicks Login button")
	public void user_clicks_login_button() {
		driver.findElement(By.id("login-button")).click();
	}

	@Then("User should navigate to inventory page")
	public void user_should_navigate_to_inventory_page() throws InterruptedException {
		boolean status = driver.getCurrentUrl().contains("inventory");
		Assert.assertTrue(status);
		Thread.sleep(3000);
	}

	@When("User enters invalid username")
	public void user_enters_invalid_username() {
		driver.findElement(By.id("user-name")).sendKeys("standarduser");
	}

	@When("User enters invalid password")
	public void user_enters_invalid_password() {
		driver.findElement(By.id("password")).sendKeys("secretsauce");
	}

	@Then("Error message should be displayed")
	public void error_message_should_be_displayed() {
		  Assert.assertTrue(driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed());
	}

}
