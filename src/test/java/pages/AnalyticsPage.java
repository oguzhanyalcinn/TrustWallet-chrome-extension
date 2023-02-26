package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnalyticsPage extends BasePage {

    public By privacyLink = By.xpath("//a[@data-test='privacy-policy-link']");

    public AnalyticsPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}