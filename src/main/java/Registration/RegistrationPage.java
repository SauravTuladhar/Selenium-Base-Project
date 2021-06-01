package Registration;

import com.sun.org.apache.bcel.internal.generic.DREM;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationPage {
    WebDriver registrationPageDriver;
    @FindBy(xpath = "//input[@placeholder='Voornaam']")
    WebElement FirstName;

    @FindBy(xpath = "//input[@placeholder='Tussenvoegsel']")
    WebElement MiddleName;

    @FindBy(xpath = "//input[@placeholder='Achternaam']")
    WebElement LastName;

    @FindBy(xpath = "//input[@placeholder='KVK nummer']")
    WebElement KvkNumber;

    @FindBy(xpath = "//*[@id=\"cdk-step-content-1-0\"]/form/ion-row/ion-col[1]/div/div/div/ion-button")
    WebElement RegisterBtn;

    @FindBy(xpath = "//input[@placeholder='Bedrijfsnaam']")
    WebElement CompanyName;

    @FindBy(xpath = "//input[@placeholder='Straat']")
    WebElement Street;

    @FindBy(xpath = "//input[@placeholder='Huisnummer en toevoeging']")
    WebElement HouseNumber;

    @FindBy(xpath = "//input[@placeholder='Postcode']")
    WebElement PostCode;

    @FindBy(xpath = "//input[@placeholder='Woonplaats']")
    WebElement Woonplaats;


    @FindBy(xpath = "//ion-button[normalize-space()='Volgende']")
    WebElement Nextbtn;

    @FindBy(xpath = " //div[@class='mat-select-value']")
    WebElement DivSubscriptionList;

    @FindBy(xpath = "//span[@class=\"mat-option-text\"]")
    List<WebElement> SubscriptionList;

    @FindBy(xpath = "//ion-radio[@role=\"radio\"]")
    List<WebElement> SubscriptionDuration;

    @FindBy(xpath = "//ion-radio[@value='Duration_Montly']")
    WebElement MonthlySubscription;

    @FindBy(xpath = " //input[@name='ion-input-13']")
    WebElement RegisterEmailAddress;

    @FindBy(xpath = "//input[@name='new-password']")
    WebElement RegisterPassword;

    @FindBy(xpath = "//input[@placeholder='Herhaal wachtwoord']")
    WebElement RegisterConfirmPassword;

    @FindBy(xpath = "//ion-checkbox[@formcontrolname='personalDataCheck']")
    WebElement AgreeCheckBox;

    @FindBy(xpath = "//ion-button[normalize-space()='Registratie voltooien']")
    WebElement CompleteRegistrationBtn;

    public RegistrationPage(WebDriver driver) {
        this.registrationPageDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName) {
        FirstName.sendKeys(firstName);
    }

    public void setMiddleName(String middleName) {
        MiddleName.sendKeys(middleName);
    }

    public void setLastName(String lastName) {
        LastName.sendKeys(lastName);
    }

    public void setKvkNumber(String kvkNumber) {
        KvkNumber.sendKeys(kvkNumber);
    }

    public void clickOnRegisterBtn() {
        registrationPageDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        executor.executeScript("arguments[0].click();", RegisterBtn);
        registrationPageDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void checkAndEditValues() throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String testValue = "Test";
        String companyNameValue = CompanyName.getAttribute("value");
        String streetValue = Street.getAttribute("value");
        String houseNumberValue = HouseNumber.getAttribute("value");
        if (companyNameValue != null) {
            CompanyName.sendKeys("Test");

        }
        if (streetValue != null) {
            registrationPageDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            Street.sendKeys(testValue);
        }
        if (houseNumberValue != null) {
            registrationPageDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            HouseNumber.sendKeys(testValue);
            Thread.sleep(10000);
        }
    }

    public void clickOnNextBtn() {
        registrationPageDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        executor.executeScript("arguments[0].click();", Nextbtn);
        registrationPageDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        executor.executeScript("scroll(0,400)");
    }

    public void clickSubscriptionList() throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
       executor.executeScript("arguments[0].scrollIntoView(true);",DivSubscriptionList);
       Thread.sleep(5000);
       executor.executeScript("arguments[0].click();",DivSubscriptionList);
    }

    public void selectSubscription()
    {
        for (WebElement element : SubscriptionList)
        {
            registrationPageDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String subscription = element.getText();
            if (subscription.equalsIgnoreCase("Test Mishu")) ;
            element.click();
        }
    }
    public void selectSubscriptionDuration() throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);",MonthlySubscription);
        Thread.sleep(5000);
        executor.executeScript("arguments[0].click();",MonthlySubscription);
    }
    public void enterEmailAddress(String registerEmailAddress) throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        Thread.sleep(5000);
        executor.executeScript("arguments[0].click();",RegisterEmailAddress);
        RegisterEmailAddress.sendKeys(registerEmailAddress);
    }

    public void enterPassword(String registerPassword)
    {
        registrationPageDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        executor.executeScript("arguments[0].click();",RegisterPassword);
        RegisterPassword.sendKeys(registerPassword);
    }

    public void enterConfirmPassword(String registerConfirmPassword) throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        Thread.sleep(5000);
        executor.executeScript("arguments[0].click();",RegisterConfirmPassword);
        RegisterConfirmPassword.sendKeys(registerConfirmPassword);
    }

    public void clickAgreeCheckBox() throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);",AgreeCheckBox);
        Thread.sleep(5000);
        executor.executeScript("arguments[0].click();",AgreeCheckBox);

    }

    public void clickConfirmRegister() throws InterruptedException {
        registrationPageDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) registrationPageDriver;
        executor.executeScript("arguments[0].click();",CompleteRegistrationBtn);
        Thread.sleep(10000);
    }
}