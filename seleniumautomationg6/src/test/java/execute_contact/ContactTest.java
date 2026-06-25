package execute_contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import freemarker.core.ParseException;
import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import object_repository.ContactPage;
import object_repository.HomePage;

@Listeners(listener_utility.List_Imp.class)
public class ContactTest extends BaseClass {

    @Test(description = "Verify successful creation and verification of a new Contact with an associated Organization")
    public void createContactTest() throws FileNotFoundException, IOException, ParseException {
        
        // 1. Initialize Utilities and Page Objects
        FileUtility futil = new FileUtility();
        WebDriverUtility wUtil = new WebDriverUtility(driver);
        HomePage hp = new HomePage(driver);
        ContactPage cp = new ContactPage(driver);

        // 2. Fetch Test Data
        String firstName = futil.getDataFromExcelFile("contact", 1, 0);
        String lastName = futil.getDataFromExcelFile("contact", 1, 1);

        // 3. Navigate to Create Contact Page
        hp.getContactlink().click();
        cp.getCreateContactIcon().click();

        // 4. Fill Contact Details
        WebElement dropdown1 = cp.getFirstNameDrop();
        wUtil.selectDropdownFromIndex(dropdown1, 1);
        Reporter.log("Selected salutation dropdown option successfully", true);

        cp.getFirstNameField().sendKeys(firstName);
        Reporter.log("Entered First Name: " + firstName, true);

        cp.getLastNameField().sendKeys(lastName);
        Reporter.log("Entered Last Name: " + lastName, true);

        // 5. Handle Child Window Navigation (Organization Selection)
        String parentWindowId = driver.getWindowHandle();
        cp.getSelectoOrgIcon().click();

        Set<String> allWindowIds = driver.getWindowHandles();
        for (String windowId : allWindowIds) {
            driver.switchTo().window(windowId);
            String currentTitle = driver.getTitle();
            Reporter.log("Switched to window with title: " + currentTitle, true);
            
            if (!windowId.equals(parentWindowId)) {
                Reporter.log("Context successfully shifted to Child Window", true);
                break;
            }
        }

        // 6. Interact with Child Window & Return to Parent Context
        cp.getSelectOrgLink().click();
        driver.switchTo().window(parentWindowId);
        Reporter.log("Returned back to Parent Window context", true);

        // 7. Save the Contact
        cp.getSaveContact().click();

        // 8. Validations & Assertions
        String actualFirstName = cp.getVerifyFirstName().getText();
        String actualLastName = cp.getVerifyLastName().getText();

        Reporter.log("Validating actual First Name [" + actualFirstName + "] against expected [" + firstName + "]", true);
        Assert.assertEquals(actualFirstName, firstName, "First Name validation failed!");

        Reporter.log("Validating actual Last Name [" + actualLastName + "] against expected [" + lastName + "]", true);
        Assert.assertEquals(actualLastName, lastName, "Last Name validation failed!");
    }
}