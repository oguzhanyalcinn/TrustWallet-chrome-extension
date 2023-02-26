package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingPage extends BasePage {

    public By createWalletButton = By.xpath("//div[@class='css-1s9f3q6']");
    public By options = By.cssSelector(".chakra-text.css-81gfuk");

    public OnboardingPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}