package genericLibraries;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImplementation implements ITestListener {
	@Override
	public void onStart(ITestContext context) {
		System.out.println("***************** Suite Execution Starts *****************");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " test success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriverUtility web = new WebDriverUtility();
		web.takeScreenshot(BaseClass.sdriver, BaseClass.sjutil, result.getMethod().getMethodName());
		System.out.println(result.getMethod().getMethodName() + " test failed");
		System.out.println("Failure occured due to: "+ result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " test skipped");
		System.out.println("Skipped due to: "+ result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("************************** Suite Execution Ends ************************");
	}

}
