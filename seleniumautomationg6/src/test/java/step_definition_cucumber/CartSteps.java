package step_definition_cucumber;

import org.openqa.selenium.By;
import org.testng.Assert;

import base_cucumber.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps extends BaseTest{

	@When("User clicks Add To Cart button")
	public void user_clicks_add_to_cart_button() {

		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
	}

	@Then("Cart badge count should be {string}")
	public void cart_badge_count_should_be(String count) {

		String actualCount = driver.findElement(By.className("shopping_cart_badge")).getText();

		Assert.assertEquals(count, actualCount);
	}

	@Given("Product is added into cart")
	public void product_is_added_into_cart() {

		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
	}

	@When("User clicks cart icon")
	public void user_clicks_cart_icon() {

		driver.findElement(By.className("shopping_cart_link")).click();
	}

	@Then("User should navigate to cart page")
	public void user_should_navigate_to_cart_page() {

		Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
	}

	@Then("Product should be visible in cart")
	public void product_should_be_visible_in_cart() {

		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed());
	}

}
