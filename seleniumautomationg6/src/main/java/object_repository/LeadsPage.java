package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getCreateleadicon() {
		return createleadicon;
	}

	public WebElement getFirstNameField() {
		return firstNameField;
	}

	public WebElement getLastNameField() {
		return lastNameField;
	}

	public WebElement getCompanyNameField() {
		return companyNameField;
	}

	public WebElement getVerifyLastName() {
		return verifyLastName;
	}

	public WebElement getVerifyCOmpany() {
		return verifyCOmpany;
	}

	@FindBy(css="img[title='Create Lead...']")
	private WebElement createleadicon ;
	
	@FindBy(name= "firstname")
	private WebElement firstNameField;
	
	@FindBy(name="lastname")
	private WebElement lastNameField;
	
	@FindBy(name="company")
	private WebElement companyNameField;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement verifyLastName ;	
	
	@FindBy(id="dtlview_Company")
	private WebElement verifyCOmpany;
	
	@FindBy(css="input[title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getSavebtn() {
		return savebtn;
	}

	
}
