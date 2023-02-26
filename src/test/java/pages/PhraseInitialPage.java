package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhraseInitialPage extends BasePage{

    public By bestPractiseLink = By.xpath("//a[@data-test='keep-safe-link']");

    public PhraseInitialPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

}
