package execute_oppo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import object_repository.HomePage;
import object_repository.OppoPage;


@Listeners(listener_utility.List_Imp.class)
public class OppoTest extends BaseClass{
	@Test
	public void createOppoTest() throws FileNotFoundException, IOException, ParseException {
		HomePage hp = new HomePage(driver);
		hp.getOpplink().click();
		OppoPage opp = new OppoPage(driver);
		opp.getCreateOppIcon().click();
		String initialoppname="chaiii";
		opp.getOppNameField().sendKeys(initialoppname);
		String pid = driver.getWindowHandle();
		System.out.println("pid windowhandle is captured");
		opp.getSelectOrgDrop().click();
		Set<String> cids = driver.getWindowHandles();
		for (String id : cids) {
			String title = driver.switchTo().window(id).getTitle();
			System.out.println(title);
			if (!id.equals(pid)) {
				System.out.println("comes to child window");
				break;
			}
		}
		opp.getSelectOrg().click();
		driver.switchTo().window(pid);
		System.out.println("returned to parent window");
		
		opp.getSaveBtn().click();
		String verifiedoppname=opp.getVerifyOppName().getText();
		boolean status= (initialoppname.equals(verifiedoppname));
		Assert.assertTrue(status);
	}
}
