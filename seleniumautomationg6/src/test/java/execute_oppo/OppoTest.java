package execute_oppo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import object_repository.HomePage;
import object_repository.OppoPage;

@Listeners(listener_utility.List_Imp.class)
public class OppoTest extends BaseClass {

    @Test(description = "Verify successful creation and verification of a new Opportunity with an associated Organization")
    public void createOppoTest() throws FileNotFoundException, IOException, ParseException {
        
        // 1. Initialize Utilities and Page Objects
        FileUtility futil = new FileUtility();
        HomePage hp = new HomePage(driver);
        OppoPage opp = new OppoPage(driver);

        // 2. Fetch Test Data
        String initialOppName = futil.getDataFromExcelFile("opp", 2, 0);

        // 3. Navigate to Create Opportunity Page
        hp.getOpplink().click();
        opp.getCreateOppIcon().click();

        // 4. Fill Opportunity Details
        opp.getOppNameField().sendKeys(initialOppName);
        Reporter.log("Entered Opportunity Name: " + initialOppName, true);

        // 5. Handle Child Window Navigation (Organization Selection Popup)
        String parentWindowId = driver.getWindowHandle();
        opp.getSelectOrgDrop().click();

        Set<String> allWindowIds = driver.getWindowHandles();
        for (String windowId : allWindowIds) {
            driver.switchTo().window(windowId);
            String currentTitle = driver.getTitle();
            Reporter.log("Switched to window with title: " + currentTitle, true);
            
            if (!windowId.equals(parentWindowId)) {
                Reporter.log("Context successfully shifted to Child Window (Organization Lookup)", true);
                break;
            }
        }

        // 6. Select Organization and Return to Parent Context
        opp.getSelectOrg().click();
        driver.switchTo().window(parentWindowId);
        Reporter.log("Returned back to Parent Window context", true);

        // 7. Save the Opportunity
        opp.getSaveBtn().click();
        Reporter.log("Clicked on Save button", true);

        // 8. Extraction and Explicit Assertion
        String verifiedOppName = opp.getVerifyOppName().getText();

        Reporter.log("Validating saved Opportunity Name [" + verifiedOppName + "] against expected [" + initialOppName + "]", true);
        Assert.assertEquals(verifiedOppName, initialOppName, "Opportunity Name validation failed!");
    }
}