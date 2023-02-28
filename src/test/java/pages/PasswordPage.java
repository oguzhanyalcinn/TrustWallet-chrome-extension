package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordPage extends BasePage {

    public By disabledProceedButton = By.xpath("//button[@type='submit' and @disabled]");
    public By proceedButton = By.xpath("//button[@type='submit']");
    public By uncheckedIcons = By.cssSelector(".chakra-icon.css-2u10vp");
    public By checkedIcons = By.cssSelector(".chakra-icon.css-19tn5g9");
    public By passwordInputBox = By.xpath("//input[@placeholder='Password']");
    public By confirmPasswordInputBox = By.xpath("//input[@placeholder='Confirm password']");
    public By passwordNotMatchText = By.xpath("//p[text()='Passwords do not match']");
    public By passwordEyeButton = By.xpath("//input[@placeholder='Password']//..//div");
    public By confirmPasswordEyeButton = By.xpath("//input[@placeholder='Confirm password']//..//div");
    public By declassifiedPasswordInput = By.xpath("//input[@type='text' and @id='field-1']");
    public By declassifiedConfirmPasswordInput = By.xpath("//input[@type='text' and @id='field-2']");
    public By termsCheckbox = By.xpath("//label[@data-test='terms-checkbox']//span[@aria-hidden]");

    public PasswordPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}