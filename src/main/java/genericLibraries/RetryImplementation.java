package genericLibraries;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class is used to re-run failed test scripts automatically
 *@author Srinivas Yadav
 */

public class RetryImplementation implements IRetryAnalyzer {
	int maxRetries = 3;
	int count;
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxRetries) {
			count++;
			return true;
		}
		return false;
	}
}
