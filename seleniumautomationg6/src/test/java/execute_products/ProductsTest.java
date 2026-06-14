package execute_products;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import object_repository.HomePage;
import object_repository.ProductPage;

@Listeners(listener_utility.List_Imp.class)
public class ProductsTest extends BaseClass{

	@Test
	public void createProductTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		
	HomePage hp = new HomePage(driver);
		hp.getProductlink().click();
		ProductPage pp = new ProductPage(driver);
		pp.getCreateProduct().click();
		//wdUtil.waitForClickable(pp.getCreateProduct(), 10).click();
		String initialPname = "samosha";
		pp.getProductNameField().sendKeys(initialPname);
		pp.getSavebtn().click();
		String verifyPname = pp.getVerifyProductName().getText();
		boolean status= (initialPname.equals(verifyPname));
		Assert.assertTrue(status);

	}
}

