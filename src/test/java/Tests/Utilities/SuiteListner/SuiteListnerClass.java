package Tests.Utilities.SuiteListner;

import Tests.Utilities.ExtentTestManager;
import org.apache.commons.mail.EmailException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.xml.sax.SAXException;

import javax.mail.internet.AddressException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static Tests.Helper.*;

public class SuiteListnerClass implements ISuiteListener
{
    @Override
    public void onFinish(ISuite suite)
    {
        try {
            sentAttachmentEmail();
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
 }
}
