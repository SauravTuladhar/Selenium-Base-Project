package Tests;

import Tests.Utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.LogManager;

import static Tests.Helper.getDriver;
import static Tests.Helper.readConfigFile;

public class BaseTest {
    static String rURL;
    static String browser;
    static String confFile;
    static String Hub;
    static WebDriver driver;

    @BeforeSuite
    @Parameters({"configFile"})
    public void getConfigFile(String configFile) {
        System.out.println("Reading Config Files");
        confFile = configFile;
        if (configFile == null)
        {
            System.out.println("No files found");
        }
    }

    @BeforeSuite(alwaysRun = true, dependsOnMethods = "getConfigFile")
    public void loadConfigFile() {
        readConfigFile(confFile);
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({"browserName"})
    public void openBrowser(String browserName) {
        browser = browserName;
    }

    static WebDriver createInstance() {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            System.out.println("Creating browser instance");
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
            LogManager.getLogManager().reset();
            driver = new ChromeDriver();
            System.out.println("Maximizing Browser Window");
            driver.manage().window().maximize();
            return driver;
        }
        return driver;
    }

    @AfterMethod
    public void tearDown()
    {
        ExtentTestManager.getTest().log(LogStatus.INFO,"CLosing Browser Instance");
        System.out.println("Closing Browser Instance");
        getDriver().quit();
    }
}
