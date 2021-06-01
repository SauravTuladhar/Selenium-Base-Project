package Tests.LoginTest;

import Registration.RegistrationPage;
import Tests.BaseRegistration;
import Tests.BaseTest;
import Tests.DataProviderClass;
import Tests.Utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import static Tests.Helper.decrypt;
import static Tests.Helper.getDriver;

public class RegistrationPageTest extends BaseTest
{
    @Test(description = "Registration Page Test",priority = 2,dataProvider ="registrationCredentials",dataProviderClass = DataProviderClass.class)
    public void registration_Test(String firstName, String middleName, String lastName, String kvkNumber, String RegisterEmailAddress,String RegisterPassword) throws InterruptedException
    {
        ExtentTestManager.getTest().log(LogStatus.INFO, "Invoking URL", "");
        System.out.println("Invoking URL");
        BaseRegistration objRegister = new BaseRegistration();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Goto Registration Page", "");
        System.out.println("Goto Registration Page");
        objRegister.goToRegistrationPage();
        RegistrationPage objRegistrationPage = new RegistrationPage(getDriver());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering first name", "");
        System.out.println("Entering first name");
        objRegistrationPage.setFirstName(firstName);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Middle Name", "");
        System.out.println("Entering Middle Name");
        objRegistrationPage.setMiddleName(middleName);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Last Name", "");
        System.out.println("Entering Last Name");
        objRegistrationPage.setLastName(lastName);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering kvkNumber", "");
        System.out.println("Entering kvkNumber");
        objRegistrationPage.setKvkNumber(kvkNumber);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Clicking Register Button", "");
        System.out.println("Clicking Register Button");
        objRegistrationPage.clickOnRegisterBtn();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Editing Values", "");
        System.out.println("Editing Values");
        objRegistrationPage.checkAndEditValues();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Clicking on Next Button", "");
        System.out.println("Clicking on Next Button");
        objRegistrationPage.clickOnNextBtn();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Clicking Subscription List", "");
        System.out.println("Clicking Subscription List");
        objRegistrationPage.clickSubscriptionList();
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Subscription", "");
            System.out.println("Selecting Subscription");
            objRegistrationPage.selectSubscription();
        }
        catch (Exception e)
        {

        }
        try{
            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Subscription Duration", "");
            System.out.println("Selecting Subscription Duration");
            objRegistrationPage.selectSubscriptionDuration();
        }catch (Exception e)
        {

        }
        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Email Address", "");
        System.out.println("Entering Email Address");
        objRegistrationPage.enterEmailAddress(RegisterEmailAddress);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Password", "");
        System.out.println("Entering Password");
        objRegistrationPage.enterPassword(decrypt(RegisterPassword));

        ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Confirm Password", "");
        System.out.println("Entering Confirm Password");
        objRegistrationPage.enterConfirmPassword(decrypt(RegisterPassword));

        ExtentTestManager.getTest().log(LogStatus.INFO, "Clicking Agree Check Box", "");
        System.out.println("Clicking Agree Check Box");
        objRegistrationPage.clickAgreeCheckBox();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Clicking Confirm Register", "");
        System.out.println("Clicking Confirm Register");
//        objRegistrationPage.clickConfirmRegister();
    }
}
