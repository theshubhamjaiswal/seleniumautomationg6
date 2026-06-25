package execute_contact;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;
import object_repository.ContactPage;
import object_repository.HomePage;
import object_repository.OrganPage;

@Listeners(listener_utility.List_Imp.class)
public class CreateContactWithOrgTest extends BaseClass {

	@Test
	public void createOrgAndContactTest() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		OrganPage op = new OrganPage(driver);
		op.getPlusicon().click();
		int randomNum = JavaUtility.getRandomNumber();
		String expectedOrgName = "AutomationWithshubham_" + randomNum;
		op.getOrgnamefield().sendKeys(expectedOrgName);
		op.getSavebtn().click();
		String actualOrgName = op.getVerifyOrgNameField().getText();
		boolean status = (actualOrgName.equals(expectedOrgName));
		Assert.assertTrue(status);

		WebDriverUtility Wutil = new WebDriverUtility(driver);
		hp.getContactlink().click();
		ContactPage cp = new ContactPage(driver);
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
		cp.getSearchBar().sendKeys(expectedOrgName);
		cp.getSearchButton().click();
		Thread.sleep(3000);
		//cp.getSearchedOrg().click(); // here we use id but we are not using it so we use dynamic xpath
		driver.findElement(By.xpath("//a[text()='" + expectedOrgName + "']")).click();  // we can not use dynamic xpath into pom model
		Thread.sleep(3000);
		driver.switchTo().window(pid);
		Thread.sleep(3000);
		System.out.println("returned to parent window");
		Thread.sleep(3000);
		cp.getSaveContact().click();

		String verifiedFirstname = cp.getVerifyFirstName().getText();
		String verifiedLastname = cp.getVerifyLastName().getText();
		Assert.assertEquals(firstName, verifiedFirstname);
		Assert.assertEquals(lastName, verifiedLastname);
		boolean status1 = (firstName.equals(verifiedFirstname) && lastName.equals(verifiedLastname));
		Assert.assertTrue(status1);
	}
}
