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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import object_repository.HomePage;
import object_repository.LoginPage;

public class BaseClass2 {

    // Upgrade to ThreadLocal to store isolated driver instances per thread
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // Public getter method to safely retrieve the current thread's driver instance
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @Parameters("browser")
    @BeforeClass(groups = {"SmokeSuite", "RegressionSuite"})
    public void setUp(@Optional("chrome") String xmlBrowser) throws FileNotFoundException, IOException, ParseException {
        FileUtility futil = new FileUtility();
        String BROWSER = "";

        if (xmlBrowser != null && !xmlBrowser.isEmpty()) {
            BROWSER = xmlBrowser;
        } else {
            BROWSER = futil.getDataFromJsonFile("bro");
        }

        WebDriver localDriver = null;

        if (BROWSER.equalsIgnoreCase("chrome")) {
            localDriver = new ChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("edge")) {
            localDriver = new EdgeDriver();
        } else if (BROWSER.equalsIgnoreCase("safari")) {
            localDriver = new SafariDriver();
        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            localDriver = new FirefoxDriver();
        } else {
            localDriver = new ChromeDriver();
        }

        // Set the driver instance into the ThreadLocal container for this thread
        threadLocalDriver.set(localDriver);

        WebDriverUtility wUtil = new WebDriverUtility(getDriver());
        wUtil.maximize();
        wUtil.implicitWait(15);
    }

    @BeforeMethod(groups = {"SmokeSuite", "RegressionSuite"})
    public void login() throws FileNotFoundException, IOException, ParseException {
        FileUtility futil = new FileUtility();
        String URL = futil.getDataFromJsonFile("url");
        
        // Pass the safe thread-bound driver instance
        getDriver().get(URL);
        LoginPage lp = new LoginPage(getDriver());
        lp.login();
    }

    @AfterMethod(groups = {"SmokeSuite", "RegressionSuite"})
    public void logout() {
        WebDriverUtility wUtil = new WebDriverUtility(getDriver());
        HomePage hp = new HomePage(getDriver());
        WebElement profileIcon = hp.getProfileicon();
        wUtil.hover(profileIcon);
        hp.getSignoutLink().click();
    }

    @AfterClass(groups = {"SmokeSuite", "RegressionSuite"})
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        if (getDriver() != null) {
            getDriver().quit();
        }
        // Memory cleanup: remove the thread configuration after execution completes
        threadLocalDriver.remove();
    }
}