package Login;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver LoginDriver;
    @FindBy(xpath = "//input[@name='username']")
    WebElement EmailTextField;

    @FindBy(xpath = "//input[@name='password']")
    WebElement PasswordTextField;

    @FindBy(xpath = "//ion-button[@type='submit']")
    WebElement LoginButton;

    @FindBy(xpath = "//img[@class='logo']")
    WebElement RechtDirectLogo;

    @FindBy(xpath="//*[@id='content']/app-login/ion-content/div/ion-row/ion-col[2]/div/div/div/ion-button")
    WebElement RegisterButton;

    public LoginPage(WebDriver driver) {
        this.LoginDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserEmail(String UserEmail) throws InterruptedException {
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(LoginDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(EmailTextField));
        EmailTextField.clear();
        EmailTextField.sendKeys(UserEmail);
    }

    public void enterUserPassword(String userPassword) {
        WebDriverWait wait = new WebDriverWait(LoginDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(PasswordTextField));
        PasswordTextField.clear();
        PasswordTextField.sendKeys(userPassword);
    }

    public void clickLoginBtn() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(LoginDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
        LoginButton.click();
        Thread.sleep(20000);
    }

    public boolean verifyMainPageLogo() {
        try {
            Boolean ImagePresent = (Boolean) ((JavascriptExecutor) LoginDriver).executeScript
                    ("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" " +
                            "&& arguments[0].naturalWidth > 0", RechtDirectLogo);
            return ImagePresent;
        } catch (Exception errorNoImage) {
            return false;
        }

    }
    public void clickOnRegisterBtn() throws InterruptedException
    {   Thread.sleep(15000);
        RegisterButton.click();
        Thread.sleep(5000);
    }
}
