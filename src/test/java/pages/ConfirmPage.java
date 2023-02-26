package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmPage extends BasePage {

    public By confirmText = By.cssSelector(".chakra-text.css-1qf743o");
    public By openWalletButton = By.xpath("//button[@data-test='open-wallet-button']");
    public By checkedSwitch = By.xpath("//span[@class='chakra-switch__track css-1e2taqv' and @data-checked]");

    public ConfirmPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
