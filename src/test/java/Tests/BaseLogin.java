package Tests;

import Login.LoginPage;
import Tests.Utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;

import static Tests.Helper.*;

public class BaseLogin extends BaseTest {
    public void validTest() {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Invoking URL", "");
        System.out.println("Invoking URL");
        LoginPage logInObj = new LoginPage(getDriver());
        try {
            invokeBrowser(prop.getProperty("BaseUrl"));
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Failed to invoke URl", e);
            System.out.println("URL not invoked");
        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Login Process Started", "");
        System.out.println("Login Process Started");
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering User Email", "");
            System.out.println("Entering User Email");
            logInObj.enterUserEmail(prop.getProperty("UserEmail"));
            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering UserPassword", "");
            System.out.println("Entering User Password");
            logInObj.enterUserPassword(decrypt(prop.getProperty("UserPassword")));
            ExtentTestManager.getTest().log(LogStatus.INFO, "Clicking on Login Button", "");
            System.out.println("Clicking on Login Button");
            logInObj.clickLoginBtn();
        } catch (Exception e) {
            System.out.println("Login Failed" + e);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed", e);
        }
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Url Validation started", "");
            System.out.println("Validating Login by URL");
            String mainPageUrl = getDriver().getCurrentUrl();
            Assert.assertEquals(mainPageUrl, prop.getProperty("BaseUrl") + "maintenance");
            System.out.println("URL Validation successful");
            ExtentTestManager.getTest().log(LogStatus.INFO, "Url Validation Success");

            try {
                ExtentTestManager.getTest().log(LogStatus.INFO, "Logo Validation Started");
                System.out.println("Validating presence of Logo");
                boolean imagePresent = logInObj.verifyMainPageLogo();
                if (imagePresent == true) {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Logo Validation Successful");
                    System.out.println("Logo Validation Successful");
                } else {
                    System.out.println("Logo not found");
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Logo not found");
                }
            } catch (Exception e) {
                System.out.println("Unable to validate logo due to " + e);
                ExtentTestManager.getTest().log(LogStatus.INFO, "Logo validation failed", e);
            }
            ExtentTestManager.getTest().log(LogStatus.PASS, "Login Successful", "");
            System.out.println("Login Successful");
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed due to", e);
            System.out.println("Login Failed due to" + e);
        }
    }

    public static void invokeBrowser(String url) {
        getDriver().get(url);

    }
}
