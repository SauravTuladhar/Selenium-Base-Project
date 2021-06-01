package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class WebDriverListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = null;
            try {
                driver = BaseTest.createInstance();
            } catch (Exception e) {
                System.out.println(" failed to set driver");
            }
            Helper.setWebDriver(driver);
        }
    }
}


