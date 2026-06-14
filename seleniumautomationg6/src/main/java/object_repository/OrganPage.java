package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganPage {

	public OrganPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="img[title='Create Organization...']")
	private WebElement plusicon;
	
	public WebElement getPlusicon() {
		return plusicon;
	}

	public WebElement getOrgnamefield() {
		return orgnamefield;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getVerifyOrgNameField() {
		return verifyOrgNameField;
	}

	@FindBy(name="accountname")
	private WebElement orgnamefield;
	
	@FindBy(css="input[title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement verifyOrgNameField;
	
	
	
	
	
}