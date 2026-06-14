package execute_leads;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import object_repository.HomePage;
import object_repository.LeadsPage;

@Listeners(listener_utility.List_Imp.class)
public class LeadsTest extends BaseClass {

	@Test
	public void createLeadTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		HomePage hp = new HomePage(driver);
		hp.getLeadslink().click();
		LeadsPage lpp = new LeadsPage(driver);
		lpp.getCreateleadicon().click();
		String initialfname = "aman";
		String initiallastname = "singh";
		String initialComName = "everest";
		lpp.getFirstNameField().sendKeys(initialfname);
		lpp.getLastNameField().sendKeys(initiallastname);
		lpp.getCompanyNameField().sendKeys(initialComName);
		lpp.getSavebtn().click();
		String verifylastname = lpp.getVerifyLastName().getText();
		String verifycompname = lpp.getVerifyCOmpany().getText();

		boolean status = (initiallastname.equals(verifylastname) && initialComName.equals(verifycompname));
		Assert.assertTrue(status);

	}
}
