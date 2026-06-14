package execute_contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import freemarker.core.ParseException;
import generic_utility.WebDriverUtility;
import object_repository.ContactPage;
import object_repository.HomePage;
@Listeners(listener_utility.List_Imp.class)
public class ContactTest extends BaseClass{
	@Test
	public void createContactTest() throws FileNotFoundException, IOException, ParseException {
		
			WebDriverUtility Wutil= new WebDriverUtility(driver);
			HomePage hp=new HomePage(driver);
			hp.getContactlink().click();
			ContactPage cp=new ContactPage(driver);
			cp.getCreateContactIcon().click();
			WebElement dropdown1 = cp.getFirstNameDrop();
			Wutil.selectDropdownFromIndex(dropdown1, 1);
			System.out.println("mr dropdown is selected");
			String firstName = "shubham";
			cp.getFirstNameField().sendKeys(firstName);
			System.out.println(firstName + "is entered as first name");
			String lastName = "jaiswal";
			cp.getLastNameField().sendKeys(lastName);
			System.out.println(lastName + "is entered as last name");
			String pid = driver.getWindowHandle();
			System.out.println("pid windowhandle is captured");
			cp.getSelectoOrgIcon().click();
			Set<String> cids = driver.getWindowHandles();
			for (String id : cids) {
				String title = driver.switchTo().window(id).getTitle();
				System.out.println(title);
				if (!id.equals(pid)) {
					System.out.println("comes to child window");
					break;
				}
			}
			cp.getSelectOrgLink().click();
			driver.switchTo().window(pid);
			System.out.println("returned to parent window");
			cp.getSaveContact().click();

			String verifiedFirstname = cp.getVerifyFirstName().getText();
			String verifiedLastname = cp.getVerifyLastName().getText();
			Assert.assertEquals(firstName,verifiedFirstname);
			Assert.assertEquals(lastName,verifiedLastname);
			boolean status= (firstName.equals(verifiedFirstname) && lastName.equals(verifiedLastname));
			Assert.assertTrue(status);		
		}
	}


