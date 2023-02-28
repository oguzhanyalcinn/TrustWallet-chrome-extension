package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public By tipDialog = By.xpath("//section[@role='dialog']");
    public By gotItButtonOnDialog = By.cssSelector("//button[text()='Got it']");
    public By closeButtonOnDialog = By.xpath("//button[@aria-label='Close']");

    public HomePage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
