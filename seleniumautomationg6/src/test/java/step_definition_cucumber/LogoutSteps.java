package step_definition_cucumber;

import org.openqa.selenium.By;
import org.testng.Assert;

import base_cucumber.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutSteps extends BaseTest {

	@When("User clicks menu button")
	public void user_clicks_menu_button() {

		driver.findElement(By.id("react-burger-menu-btn")).click();
	}

	@When("User clicks logout link")
	public void user_clicks_logout_link() {

		driver.findElement(By.id("logout_sidebar_link")).click();
	}

	@Then("User should navigate to login page")
	public void user_should_navigate_to_login_page() {

		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/"));
	}

}
