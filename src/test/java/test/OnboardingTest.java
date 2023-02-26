package test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.OnboardingPage;
import util.Constants;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class OnboardingTest extends BaseTest {

    @Test
    public void createWalletButton_isAvailable() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);

        onboardingPage.elementDisplayed(onboardingPage.createWalletButton);

        List<WebElement> options = driver.findElements(onboardingPage.options);
        assertEquals(options.get(0).getText(), "Create a new wallet");
        assertEquals(options.get(1).getText(), "Import or recover wallet");
        assertEquals(options.get(2).getText(), "Ledger");
    }

    @Test
    public void createButton_shouldNavigateTo_passwordPage() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        wait.until(ExpectedConditions.urlToBe(Constants.PASSWORD_PAGE));
    }
}