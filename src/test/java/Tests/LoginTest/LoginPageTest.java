package Tests.LoginTest;

import Tests.BaseLogin;
import Tests.BaseTest;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    @Test(description = "Simple Login Test", priority = 1)
    public void login_Test() {
        BaseLogin objBaseLogin = new BaseLogin();
        objBaseLogin.validTest();
    }
}

