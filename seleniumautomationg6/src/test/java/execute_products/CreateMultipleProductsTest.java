package execute_products;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base_utility.BaseClass;
import generic_utility.JavaUtility;
import object_repository.HomePage;
import object_repository.ProductPage;
@Listeners(listener_utility.List_Imp.class)
public class CreateMultipleProductsTest extends BaseClass {

    @DataProvider(name = "productDataProvider")
    public Object[][] getProductData() {
        // Data matrix containing unique product categories
        return new Object[][] {
            {"aata"},
            {"daal"},
            {"chaval"}
        };
    }

    @Test(dataProvider = "productDataProvider", groups = {"RegressionSuite"}, description = "Data-driven creation of variant products")
    public void createProductWithDataTest(String productCategoryName) {
        HomePage hp = new HomePage(driver);
        ProductPage pp = new ProductPage(driver);
        
        int generationId = JavaUtility.getRandomNumber();
        String compositeProductName = productCategoryName + "_" + generationId;

        hp.getProductlink().click();
        pp.getCreateProduct().click();

        pp.getProductNameField().sendKeys(compositeProductName);
        Reporter.log("Data-Driven Input - Creating Product: " + compositeProductName, true);
        
        pp.getSavebtn().click();

        String parsedName = pp.getVerifyProductName().getText();
        Assert.assertEquals(parsedName, compositeProductName, "Product field synchronization failed.");
    }
}