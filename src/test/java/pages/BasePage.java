package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public By backButton = By.xpath("//button[@data-test='cancel-button']");
    public By approveButton = By.xpath("//button[@data-test='approve-button']");
    public By disabledApproveButton = By.xpath("//button[@data-test='approve-button' and @disabled]");

    public ChromeDriver driver;
    public WebDriverWait wait;

    public BasePage(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(text);
    }

    public void elementDisplayed(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}