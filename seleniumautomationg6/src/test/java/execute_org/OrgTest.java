package execute_org;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import object_repository.HomePage;
import object_repository.OrganPage;

@Listeners(listener_utility.List_Imp.class)
public class OrgTest extends BaseClass {

	@Test(groups = {"SmokeSuite", "RegressionSuite"}, description = "Verify successful creation of an organization")
	public void createOrgTest() throws InterruptedException, EncryptedDocumentException, IOException {
        
        // 1. Initialize Utilities and Page Objects
        FileUtility futil = new FileUtility();
        HomePage hp = new HomePage(driver);
        OrganPage op = new OrganPage(driver);

        // 2. Fetch Base Test Data and Append Runtime Randomization
        int randomNum = JavaUtility.getRandomNumber();
        String baseOrgName = futil.getDataFromExcelFile("org", 1, 0);
        String expectedOrgName = baseOrgName + randomNum;

        // 3. Navigate to Create Organization Page
        hp.getOrglink().click();
        op.getPlusicon().click();
        Reporter.log("Navigated to 'Create Organization' layout", true);

        // 4. Fill Details and Save
        op.getOrgnamefield().sendKeys(expectedOrgName);
        Reporter.log("Entered Organization Name with random suffix: " + expectedOrgName, true);

        op.getSavebtn().click();
        Reporter.log("Clicked on Save button", true);

        // 5. Verification and Field Validation
        String actualOrgName = op.getVerifyOrgNameField().getText();

        Reporter.log("Validating actual Organization Name [" + actualOrgName + "] against expected [" + expectedOrgName + "]", true);
        Assert.assertEquals(actualOrgName, expectedOrgName, "Organization Name verification failed!");
    }
}