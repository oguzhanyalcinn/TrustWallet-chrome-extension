package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyPage extends BasePage {

    public By phrases = By.cssSelector(".css-u5eit4");
    public By selectedPhrases = By.cssSelector(".css-1ibdiu9");
    public By invalidSecretText = By.cssSelector(".chakra-text.css-8mylp6");
    public By crossButton = By.cssSelector(".chakra-icon.css-a7f3a8");

    public VerifyPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnSpecificPhrase(String value) {
        click(By.xpath("//input[@value='" + value + "']"));
    }


}
