package Tests;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Helper {
    WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static Properties prop;
    public static String dataFolderBasePath = "Data/Config/";
    public static String storedTimeStamp = timeStamp();

    public static String timeStamp() {
        DateFormat timeFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String timeStamp = timeFormat.format(date);
        return timeStamp;
    }

    static {
        prop = new Properties();
    }

    //Creating instance of webDriver and returning from helper class
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    //Method to read config XML file
    public static void readConfigFile(String configFile) {
        try {

            InputStream file = new FileInputStream(dataFolderBasePath + configFile);
            prop.loadFromXML(file);
            if (file == null) {
                System.out.println("null file");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to encrypt userpassword
    private static final String key = "aesEncryptionKey";

    public static String encrypt(String userId) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES"); //CBC/PKCS5PADDING/AES
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(userId.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Method to decrypt Userpassword
    public static String decrypt(String encrypted) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Main method to run and test methods of helper class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        System.out.println("The encrypted pw is:" + encrypt(password));
        System.out.println(" The real password after decryption is: " + decrypt(encrypt(password)));
    }
        // Method to send Email with Extent Report Attachment In It
    public static void sentAttachmentEmail() throws EmailException, AddressException, IOException, SAXException, ParserConfigurationException {


        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(prop.getProperty("emailId"), decrypt(prop.getProperty("passwordEmail")));
                    }
                });
        try {
            // -- Create a new message --
            Message messages = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            messages.setFrom(new InternetAddress(prop.getProperty("emailId")));
            messages.setRecipients(Message.RecipientType.TO, InternetAddress.parse(prop.getProperty("emailSentTo"), false));
            messages.setRecipients(Message.RecipientType.CC, InternetAddress.parse(prop.getProperty("emailSentToCC"), false));
            messages.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(prop.getProperty("emailSentToBCC"), false));

            messages.setSubject("Test automation Report");

            BodyPart messageBodyPart = new javax.mail.internet.MimeBodyPart();

//            messageBodyPart.setContent("<b> <font color=#000000> Test overview : </b> \n" + "<br> Environment : " + environment + "<br> Domain : " + domain + "<br> User : " + prop.getProperty("validUserName") + getResult() + "\n <b> <i> <font color=#000000> Note : Detailed Test Automation report is attached in mail. </i> </b>", "text/html");
            messageBodyPart.setContent("<b> <font color=#000000> Test overview : </b> \n" + "<br> URL : " + prop.getProperty("BaseUrl") + "<br> User Email : " + prop.getProperty("UserEmail") + "<br>" + "\n <b> <i> <font color=#000000> Note : Detailed Test Automation report is attached in mail. </i> </b>", "text/html");

            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();

            //messages.setSentDate(new Date());

            String filename = "./Reports/AutomationReport" + storedTimeStamp + ".html";

            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            //messageBodyPart.setFileName(filename);
            messageBodyPart.setFileName(new File(filename).getName());
            multipart.addBodyPart(messageBodyPart);
            messages.setContent(multipart);

            Transport.send(messages);
            System.out.println("=====Email Sent=====");
            //Extent report
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

