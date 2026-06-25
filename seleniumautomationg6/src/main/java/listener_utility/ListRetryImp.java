package listener_utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ListRetryImp implements IRetryAnalyzer {
	int initialCount = 0;
	int count = 7;

	@Override
	public boolean retry(ITestResult result) {
		while (count > initialCount) {
			initialCount++;
			return true;
		}

		return false;
	}

}
