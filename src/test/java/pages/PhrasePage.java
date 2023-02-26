package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhrasePage extends BasePage{

    public By copyPhraseButton = By.xpath("//button[@data-test='copy-phrase-icon']");
    public By blurredPhrase = By.cssSelector(".css-26igns");
    public By nonBlurredPhrase = By.cssSelector(".css-xihjuc");

    public PhrasePage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
