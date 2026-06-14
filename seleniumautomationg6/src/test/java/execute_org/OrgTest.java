package execute_org;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.JavaUtility;
import object_repository.HomePage;
import object_repository.OrganPage;

@Listeners(listener_utility.List_Imp.class)
public class OrgTest extends BaseClass {
	@Test
	public void createOrgTest() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		OrganPage op = new OrganPage(driver);
		op.getPlusicon().click();
		int randomNum = JavaUtility.getRandomNumber();
		String expectedOrgName = "AutomationWithPiyush_" + randomNum;
		op.getOrgnamefield().sendKeys(expectedOrgName);
		op.getSavebtn().click();
		String actualOrgName = op.getVerifyOrgNameField().getText();
		boolean status = (actualOrgName.equals(expectedOrgName));
		Assert.assertTrue(status);

	}
}
