package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getOpplink() {
		return opplink;
	}

	public WebElement getProductlink() {
		return productlink;
	}

	public WebElement getLeadslink() {
		return leadslink;
	}

	public WebElement getProfileicon() {
		return profileicon;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}

	@FindBy(linkText = "Organizations")
	private WebElement orglink;

	@FindBy(linkText = "Contacts")
	private WebElement contactlink;

	@FindBy(linkText = "Opportunities")
	private WebElement opplink;

	@FindBy(linkText = "Products")
	private WebElement productlink;

	@FindBy(linkText = "Leads")
	private WebElement leadslink;

	@FindBy(css = "img[src='themes/softed/images/user.PNG']")
	private WebElement profileicon;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;

}
