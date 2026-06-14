package generic_utility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	WebDriver driver;
	Actions act;
	WebDriverWait wait;

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void implicitWait(int timeoutOfsecond) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutOfsecond));
	}

	public void hover(WebElement element) {
		act = new Actions(driver);
		act.moveToElement(element);
	}

	public void waitForElemetVisible(WebElement element, int timeoutOfsecond) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutOfsecond));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForClickable(WebElement element, int timeoutinsecond) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeoutinsecond))
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void selectDropdownFromIndex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void switchToChildWindow(String pid) {
		Set<String> allWindowIds = driver.getWindowHandles();

		for (String windowId : allWindowIds) {
			String title = driver.switchTo().window(windowId).getTitle();
			System.out.println("INFO: Switched to Window Title -> " + title);

			if (!windowId.equals(pid)) {
				System.out.println("INFO: Context successfully shifted to Child Window.");
				break;
			}

		}
	}
	public void switchToParentWindow(String pid) {
		driver.switchTo().window(pid);
	}
}
