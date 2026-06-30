package step_definition_cucumber;

import org.openqa.selenium.By;
import org.testng.Assert;

import base_cucumber.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class InventorySteps extends BaseTest {

	@Given("User is logged into application")
	public void user_is_logged_into_application() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();
	}

	@Then("Inventory page title should be Products")
	public void inventory_page_title_should_be_products() {

		String title = driver.findElement(By.className("title")).getText();

		Assert.assertEquals("Products", title);
	}

	@Then("Product list should be displayed")
	public void product_list_should_be_displayed() {

		int count = driver.findElements(By.className("inventory_item")).size();

		Assert.assertTrue(count > 0);
	}

	@Then("Cart icon should be displayed")
	public void cart_icon_should_be_displayed() {

		Assert.assertTrue(driver.findElement(By.className("shopping_cart_link")).isDisplayed());
	}
}
