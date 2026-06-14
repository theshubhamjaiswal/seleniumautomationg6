package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OppoPage {

	public OppoPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
}
	public WebElement getCreateOppIcon() {
		return createOppIcon;
	}

	public WebElement getOppNameField() {
		return oppNameField;
	}

	public WebElement getSelectOrgDrop() {
		return selectOrgDrop;
	}

	public WebElement getSelectOrg() {
		return selectOrg;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getVerifyOppName() {
		return verifyOppName;
	}
	@FindBy(css="img[title='Create Opportunity...']")
	private WebElement createOppIcon ;
	
	
	@FindBy(css="input[name='potentialname']")
	private WebElement oppNameField  ;
	
	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img")
	private WebElement  selectOrgDrop;  ;
	
	
	@FindBy(linkText = "shubhamautomation_org")
	private WebElement selectOrg;   ;
	
	@FindBy(css="input[title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(id="dtlview_Opportunity Name")
	private WebElement verifyOppName;
	
}