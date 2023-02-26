package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordPage extends BasePage {

    public By disabledProceedButton = By.xpath("//button[@type='submit' and @disabled]");
    public By proceedButton = By.xpath("//button[@type='submit']");
    public By uncheckedIcons = By.cssSelector(".chakra-icon.css-2u10vp");
    public By checkedIcons = By.cssSelector(".chakra-icon.css-19tn5g9");
    public By passwordInputBox = By.cssSelector("#field-1");
    public By confirmPasswordInputBox = By.cssSelector("#field-2");
    public By passwordNotMatchText = By.cssSelector(".chakra-text.css-1e6tq1p");
    public By passwordEyeButton = By.xpath("//input[@placeholder='Password']//..//div");
    public By confirmPasswordEyeButton = By.xpath("//input[@placeholder='Confirm password']//..//div");
    public By declassifiedPasswordInput = By.xpath("//input[@type='text' and @id='field-1']");
    public By declassifiedConfirmPasswordInput = By.xpath("//input[@type='text' and @id='field-2']");
    public By termsCheckbox = By.cssSelector(".chakra-checkbox__control.css-3bjp2t");

    public PasswordPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}