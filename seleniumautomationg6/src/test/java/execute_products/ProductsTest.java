package execute_products;

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
import object_repository.ProductPage;

@Listeners(listener_utility.List_Imp.class)
public class ProductsTest extends BaseClass {

    @Test(description = "Verify successful creation and confirmation of a new Product record")
    public void createProductTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
        
        // 1. Initialize Utilities and Page Objects
        FileUtility futil = new FileUtility();
        HomePage hp = new HomePage(driver);
        ProductPage pp = new ProductPage(driver);

        // 2. Fetch Test Data up front
        String initialPname = futil.getDataFromExcelFile("products", 1, 0);

        // 3. Navigate to Create Product Page
        hp.getProductlink().click();
        pp.getCreateProduct().click();
        Reporter.log("Navigated to 'Create Product' page Layout", true);

        // 4. Fill Product Information and Save
        pp.getProductNameField().sendKeys(initialPname);
        Reporter.log("Entered Product Name: " + initialPname, true);

        pp.getSavebtn().click();
        Reporter.log("Clicked on Save button", true);

        // 5. Verification and Field Extraction
        String verifyPname = pp.getVerifyProductName().getText();

        // 6. Senior Assertion Pattern (Direct Comparison with Failure Log)
        Reporter.log("Validating actual Product Name [" + verifyPname + "] against expected [" + initialPname + "]", true);
        Assert.assertEquals(verifyPname, initialPname, "Product Name validation failed!");
    }
}