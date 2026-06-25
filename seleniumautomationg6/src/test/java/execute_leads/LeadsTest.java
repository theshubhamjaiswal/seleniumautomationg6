package execute_leads;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import object_repository.HomePage;
import object_repository.LeadsPage;

@Listeners(listener_utility.List_Imp.class)
public class LeadsTest extends BaseClass {

	@Test(description = "Verify successful creation and validation of a new Lead record")
	public void createLeadTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		// 1. Initialize Utilities and Page Objects
		FileUtility futil = new FileUtility();
		HomePage hp = new HomePage(driver);
		LeadsPage lpp = new LeadsPage(driver);

		// 2. Fetch Test Data up front (Clean separation of concerns)
		String initialFname = futil.getDataFromExcelFile("leads", 1, 0);
		String initialLastname = futil.getDataFromExcelFile("leads", 1, 1);
		String initialComName = futil.getDataFromExcelFile("leads", 1, 2);

		// 3. Navigate to Create Lead Page
		hp.getLeadslink().click();
		lpp.getCreateleadicon().click();
		Reporter.log("Navigated to the 'Create Lead' page", true);

		// 4. Populate Lead Information
		lpp.getFirstNameField().sendKeys(initialFname);
		Reporter.log("Entered Lead First Name: " + initialFname, true);

		lpp.getLastNameField().sendKeys(initialLastname);
		Reporter.log("Entered Lead Last Name: " + initialLastname, true);

		lpp.getCompanyNameField().sendKeys(initialComName);
		Reporter.log("Entered Company Name: " + initialComName, true);

		// 5. Save the Record
		lpp.getSavebtn().click();
		Reporter.log("Clicked on Save button", true);

		// 6. UI Text Verification and Extraction
		String verifiedLastname = lpp.getVerifyLastName().getText();
		String verifiedCompname = lpp.getVerifyCOmpany().getText();

		// 7. Explicit Assertions with Custom Failure Messages
		Reporter.log("Validating saved Last Name [" + verifiedLastname + "] against expected [" + initialLastname + "]",true);
		Assert.assertEquals(verifiedLastname, initialLastname, "Lead Last Name validation failed!");

		Reporter.log(
				"Validating saved Company Name [" + verifiedCompname + "] against expected [" + initialComName + "]",true);
		Assert.assertEquals(verifiedCompname, initialComName, "Lead Company Name validation failed!");
	}
}