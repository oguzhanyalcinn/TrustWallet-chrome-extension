package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public By tipDialog = By.cssSelector("#chakra-modal-1");
    public By gotItButtonOnDialog = By.cssSelector(".chakra-button.css-19yp25y");
    public By closeButtonOnDialog = By.xpath("//button[@aria-label='Close']");

    public HomePage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
