package base_utility;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import object_repository.HomePage;
import object_repository.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException {
		FileUtility futil = new FileUtility();
		String BROWSER = futil.getDataFromJsonFile("bro");
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		sdriver = driver;

		WebDriverUtility Wutil = new WebDriverUtility(driver);
		Wutil.maximize();
		Wutil.implicitWait(15);

	}

	@BeforeMethod
	public void login() throws FileNotFoundException, IOException, ParseException {
		FileUtility futil = new FileUtility();
		String URL = futil.getDataFromJsonFile("url");
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.login();
		System.out.println("Login successful");
	}

	@AfterMethod
	public void logout() {
		WebDriverUtility Wutil = new WebDriverUtility(driver);
		HomePage hp = new HomePage(driver);
		WebElement profileIcon = hp.getProfileicon();
		Wutil.hover(profileIcon);
		hp.getSignoutLink().click();
		System.out.println("Logout successful");
	}

	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Browser closed successfully");
		driver.quit();
	}

}
