package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "img[title='Create Product...']")
	private WebElement createProduct;

	public WebElement getCreateProduct() {
		return createProduct;
	}

	public WebElement getProductNameField() {
		return productNameField;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getVerifyProductName() {
		return verifyProductName;
	}

	@FindBy(css = "input[name='productname']")
	private WebElement productNameField;

	@FindBy(css = "input[title='Save [Alt+S]']")
	private WebElement savebtn;

	@FindBy(id = "dtlview_Product Name")
	private WebElement verifyProductName;

}