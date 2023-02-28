package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingPage extends BasePage {

    public By createWalletButton = By.xpath("//h1[text()='Create a new wallet']");
    public By options = By.xpath("//div[@data-test='welcome-button']//div//h1");

    public OnboardingPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}