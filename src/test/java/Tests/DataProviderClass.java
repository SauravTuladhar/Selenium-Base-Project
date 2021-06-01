package Tests;
import org.testng.annotations.DataProvider;
import static Tests.Helper.prop;

public class DataProviderClass {
    @DataProvider(name = "loginCredentials")
    public Object[][] returnLoginCredentials() {
        Object[][] path = new Object[1][2];
        path[0][0] = prop.getProperty("UserEmail");
        path[0][1] = prop.getProperty("UserPassword");
        return path;
    }

    @DataProvider(name = "registrationCredentials")
    public Object[][] returnRegistrationCredentials()
    {
        Object[][] path = new Object[1][6];
        path[0][0] = prop.getProperty("FirstName");
        path[0][1] = prop.getProperty("MiddleName");
        path[0][2]= prop.getProperty("LastName");
        path[0][3]= prop.getProperty("KvkNumber");
        path[0][4]=prop.getProperty("RegisterEmailAddress");
        path[0][5]=prop.getProperty("RegisterPassword");
        return path;
    }
}
