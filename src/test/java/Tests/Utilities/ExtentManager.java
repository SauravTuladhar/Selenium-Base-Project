package Tests.Utilities;

import com.relevantcodes.extentreports.ExtentReports;

import static Tests.Helper.storedTimeStamp;

public class ExtentManager
{
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir + "\\Reports\\AutomationReport" + storedTimeStamp + ".html", true);
        }
        return extent;
    }
}
