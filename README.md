# 🧪 Selenium Hybrid Automation Framework (G6)

A professional-grade **Hybrid Test Automation Framework** built with **Selenium WebDriver + TestNG + Java**, implementing **DDT (Data Driven Testing)**, **TDD (Test Driven Development)**, and **BDD (Behaviour Driven Development)** methodologies — with **Page Object Model**, **Extent Reports**, and **parallel XML execution**.

---

## 🚀 Framework Evolution Journey

This project was built step-by-step to demonstrate how a real automation framework matures:

```
Stage 1: Hardcoded Scripts
   ↓
Stage 2: DDT — Reading data from JSON, Properties & Excel
   ↓
Stage 3: POM — Eliminating hardcoded WebElements with Page Objects
   ↓
Stage 4: TestNG — Annotations, Attributes, Listeners & Base Class
   ↓
Stage 5: Extent Reports + Screenshots on Failure
   ↓
Stage 6: XML Suite Execution — Parallel, Group & Suite control
```

---

## 🏗️ Project Structure

```
seleniumautomationg6/
├── src/
│   ├── main/java/
│   │   ├── pages/               # Page Object classes (POM)
│   │   │   ├── LoginPage.java
│   │   │   ├── HomePage.java
│   │   │   └── ProductPage.java
│   │   ├── base/
│   │   │   └── BaseClass.java   # Setup, teardown, config loading
│   │   ├── listeners/
│   │   │   └── MyListener.java  # ITestListener implementation
│   │   └── utils/
│   │       ├── ExcelUtils.java  # Apache POI helper
│   │       ├── JsonUtils.java   # Gson/JSON reader
│   │       └── ConfigReader.java # Properties file reader
│   └── test/java/
│       └── tests/
│           ├── LoginTest.java
│           ├── HomeTest.java
│           └── ProductTest.java
├── testdata/
│   ├── config.properties        # Base URL, browser, credentials
│   ├── testdata.json            # JSON test data
│   └── testdata.xlsx            # Excel test data (Apache POI)
├── testng.xml                   # Suite XML — parallel execution
├── screenshots/                 # Auto-captured on test failure
├── reports/                     # Extent HTML reports
└── pom.xml                      # Maven dependencies
```

---

## ⚙️ Tech Stack & Dependencies

| Tool / Library | Version | Purpose |
|---|---|---|
| Java | 11+ | Primary language |
| Selenium WebDriver | 4.x | Browser automation |
| TestNG | 7.x | Test framework |
| Apache POI | 5.x | Excel (.xlsx) reading |
| Gson | 2.x | JSON parsing |
| ExtentReports | 5.x | Advanced HTML reporting |
| Maven | 3.x | Build & dependency management |
| ChromeDriver / GeckoDriver | Latest | Browser drivers |

---

## 📦 Stage 1 — Hardcoded Scripts (The Starting Point)

Before the framework was built, tests had everything hardcoded:

```java
// ❌ Hardcoded — fragile and unscalable
driver.get("https://demo.nopcommerce.com");
driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
driver.findElement(By.id("Password")).sendKeys("admin");
driver.findElement(By.cssSelector(".login-button")).click();
```

**Problems:**
- Data changes require code edits
- Locators are scattered across all test files
- Cannot run multiple datasets without duplicating tests

---

## 🗄️ Stage 2 — Data Driven Testing (DDT)

Test data is now read from **external sources**, completely decoupled from code.

### config.properties
```properties
url=https://demo.nopcommerce.com
browser=chrome
username=admin@yourstore.com
password=admin
```

### testdata.json
```json
[
  { "username": "user1@test.com", "password": "Pass@1" },
  { "username": "user2@test.com", "password": "Pass@2" }
]
```

### Excel (testdata.xlsx) via Apache POI
```java
public static Object[][] readData(String filePath, String sheetName) {
    // Uses Apache POI to read rows and return Object[][]
}
```

### @DataProvider
```java
@DataProvider(name = "loginData")
public Object[][] getLoginData() throws IOException {
    return ExcelUtils.readData("testdata/testdata.xlsx", "LoginSheet");
}

@Test(dataProvider = "loginData")
public void loginTest(String user, String pass) {
    loginPage.login(user, pass);
    Assert.assertTrue(homePage.isLoggedIn());
}
```

---

## 🧩 Stage 3 — Page Object Model (POM)

All WebElement locators are centralized in dedicated Page classes using `@FindBy` and `PageFactory`.

```java
// LoginPage.java
public class LoginPage {

    WebDriver driver;

    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(id = "Password")
    WebElement passwordField;

    @FindBy(css = ".login-button")
    WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String pass) {
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        loginBtn.click();
    }
}
```

**Benefits:**
- Change a locator in one place — all tests updated automatically
- Tests read like plain English (`loginPage.login(user, pass)`)
- Easy to maintain as the application UI evolves

---

## 🔬 Stage 4 — TestNG: Annotations, Attributes & Listeners

### Annotation Flow

```
@BeforeSuite → @BeforeClass → @BeforeMethod → @Test → @AfterMethod → @AfterClass → @AfterSuite
```

### @Test Helper Attributes

```java
@Test(
    priority = 1,
    groups = {"smoke", "regression"},
    dependsOnMethods = {"loginTest"},
    enabled = true,
    retryAnalyzer = RetryAnalyzer.class,
    dataProvider = "loginData",
    timeout = 5000,
    invocationCount = 1
)
public void verifyDashboard() { ... }
```

### ITestListener

```java
public class MyListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Auto-capture screenshot on every failure
        String screenshotPath = captureScreenshot(result.getName());
        test.fail("Test failed", MediaEntityBuilder
            .createScreenCaptureFromPath(screenshotPath).build());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed ✅");
    }
}
```

### BaseClass

```java
public class BaseClass {

    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.createInstance("reports/ExtentReport.html");
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = new ChromeDriver();
        driver.get(ConfigReader.getProperty("url"));
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
```

---

## 📊 Stage 5 — Extent Reports & Screenshots

- **ExtentHtmlReporter** generates a rich interactive HTML report
- **Screenshots** captured automatically on failure via `TakesScreenshot`
- **ExtentTest** logs each step: `test.pass()`, `test.fail()`, `test.info()`, `test.skip()`
- Reports saved under `/reports/` with timestamp in filename

```java
// Screenshot utility
public static String captureScreenshot(WebDriver driver, String testName) {
    TakesScreenshot ts = (TakesScreenshot) driver;
    File src = ts.getScreenshotAs(OutputType.FILE);
    String path = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
    FileUtils.copyFile(src, new File(path));
    return path;
}
```

---

## ▶️ Stage 6 — XML Suite Execution

```xml
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="RegressionSuite" parallel="methods" thread-count="3">

    <listeners>
        <listener class-name="listeners.MyListener"/>
    </listeners>

    <test name="SmokeTests">
        <groups>
            <run>
                <include name="smoke"/>
            </run>
        </groups>
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.HomeTest"/>
        </classes>
    </test>

</suite>
```

**Run from terminal:**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 🏛️ Complete Architecture Diagram

```
┌─────────────────────────────────────────────────┐
│              testng.xml (Suite Control)         │
├─────────────────────────────────────────────────┤
│         BaseClass (Setup · Config · Teardown)   │
├─────────────────────────────────────────────────┤
│    Test Classes (@Test · @DataProvider · Groups)│
├─────────────────────────────────────────────────┤
│       Page Objects (POM · @FindBy · Actions)    │
├─────────────────────────────────────────────────┤
│    DDT Layer (JSON · Properties · Excel)        │
├─────────────────────────────────────────────────┤
│  Listeners + Extent Reports + Screenshots       │
└─────────────────────────────────────────────────┘
```

---

## 🏃 How to Run

**Prerequisites:** Java 11+, Maven 3.x, Chrome browser installed

```bash
# Clone the repository
git clone https://github.com/theshubhamjaiswal/seleniumautomationg6.git
cd seleniumautomationg6

# Install dependencies
mvn clean install -DskipTests

# Run all tests via XML suite
mvn test -DsuiteXmlFile=testng.xml

# Run specific group (smoke only)
# Edit testng.xml to include/exclude groups, then:
mvn test
```

Reports are generated at `reports/ExtentReport.html` — open in any browser.

---

## 🤝 Contributing

Fork the repo, create a feature branch, commit your changes, and open a Pull Request.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
