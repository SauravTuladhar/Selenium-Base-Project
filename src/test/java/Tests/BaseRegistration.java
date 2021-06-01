package Tests;

import Login.LoginPage;

import static Tests.BaseLogin.invokeBrowser;
import static Tests.Helper.getDriver;
import static Tests.Helper.prop;

public class BaseRegistration extends BaseTest
{
  public void goToRegistrationPage() throws InterruptedException {
        LoginPage login = new LoginPage(getDriver());
        invokeBrowser(prop.getProperty("BaseUrl"));
       login.clickOnRegisterBtn();
    }
}
