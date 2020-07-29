package listeners;

import helpers.SeleniumUtils;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestResultsListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        SeleniumUtils.getInstance().alert("Test FAILED, driver will be closed in a" +
                " few seconds.....");
        SeleniumUtils.getInstance().getDriver().quit();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        SeleniumUtils.getInstance().alert("Test PASSED, driver will be closed in a" +
                " few seconds.....");
        SeleniumUtils.getInstance().getDriver().quit();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        SeleniumUtils.getInstance().alert("Test SKIPPED, driver will be closed in a" +
                " few seconds.....");
        SeleniumUtils.getInstance().getDriver().quit();
    }
}
