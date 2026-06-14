package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	private WebElement unfield;
	
	@FindBy(name="user_password")
	private WebElement pwdfield;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	
	
	public WebElement getUnfield() {
		return unfield;
	}

	public WebElement getPwdfield() {
		return pwdfield;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	
	public void login() {
		unfield.sendKeys("admin");
		pwdfield.sendKeys("manager");
		loginbtn.click();
	}
	
}
