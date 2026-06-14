package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
	public WebElement getCreateContactIcon() {
		return createContactIcon;
	}

	public WebElement getFirstNameDrop() {
		return firstNameDrop;
	}

	public WebElement getFirstNameField() {
		return firstNameField;
	}

	public WebElement getLastNameField() {
		return lastNameField;
	}

	public WebElement getSelectoOrgIcon() {
		return selectoOrgIcon;
	}

	public WebElement getSelectOrgLink() {
		return selectOrgLink;
	}

	public WebElement getSaveContact() {
		return saveContact;
	}

	public WebElement getVerifyFirstName() {
		return verifyFirstName;
	}

	public WebElement getVerifyLastName() {
		return verifyLastName;
	}

	@FindBy(css="img[title='Create Contact...']")
	private WebElement createContactIcon;
		
	@FindBy(name="salutationtype")	
	private WebElement firstNameDrop;
		
	@FindBy(name="firstname")
	private WebElement firstNameField;
	
	@FindBy(name="lastname")
	private WebElement lastNameField;
	
	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	private WebElement selectoOrgIcon;
	
	@FindBy(linkText = "shubhamautomation_org")
	private WebElement selectOrgLink;
	
	@FindBy(css="input[title='Save [Alt+S]']")
	private WebElement saveContact;
	
	@FindBy(id="dtlview_First Name")
	private WebElement verifyFirstName;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement verifyLastName;
	
	}
	

