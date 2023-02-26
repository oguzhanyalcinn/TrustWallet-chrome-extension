package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import util.Constants;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public ChromeDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("1.0.5_0.crx"));
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get(Constants.ONBOARDING_PAGE);
        driver.manage().deleteAllCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    public void goToPhraseInitialPage() throws InterruptedException {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.sendKeys(passwordPage.passwordInputBox, "123.Oguzhan");
        passwordPage.sendKeys(passwordPage.confirmPasswordInputBox, "123.Oguzhan");
        passwordPage.click(passwordPage.termsCheckbox);
        passwordPage.click(passwordPage.proceedButton);
        Thread.sleep(3000);

        // This is a bug. I had to enter the passwords twice.
        passwordPage.sendKeys(passwordPage.passwordInputBox, "123.Oguzhan");
        passwordPage.sendKeys(passwordPage.confirmPasswordInputBox, "123.Oguzhan");
        passwordPage.click(passwordPage.termsCheckbox);
        passwordPage.click(passwordPage.proceedButton);

        wait.until(ExpectedConditions.urlToBe(Constants.PHRASE_INITIATE_PAGE));
    }

    public void goToPhrasePage() throws InterruptedException {
        PhraseInitialPage phraseInitialPage = new PhraseInitialPage(driver, wait);

        goToPhraseInitialPage();
        phraseInitialPage.click(phraseInitialPage.approveButton);
        wait.until(ExpectedConditions.urlToBe(Constants.PHRASE_PAGE));
    }

    public String goToVerifyPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        PhrasePage phrasePage = new PhrasePage(driver, wait);

        goToPhrasePage();
        phrasePage.click(phrasePage.approveButton);
        phrasePage.click(phrasePage.copyPhraseButton);

        String myPhrase = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

        phrasePage.click(phrasePage.approveButton);
        wait.until(ExpectedConditions.urlToBe(Constants.VERIFY_PAGE));

        return myPhrase;
    }

    public void goToAnalyticsPage() throws IOException, InterruptedException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        String myPhrase = goToVerifyPage();

        for (String s : myPhrase.split(" ")) {
            verifyPage.clickOnSpecificPhrase(s);
        }

        verifyPage.click(verifyPage.approveButton);
    }

    public void goToHomePage() throws IOException, InterruptedException, UnsupportedFlavorException {
        ConfirmPage confirmPage = new ConfirmPage(driver, wait);

        goToAnalyticsPage();
        confirmPage.click(confirmPage.approveButton);
        confirmPage.click(confirmPage.openWalletButton);
        wait.until(ExpectedConditions.urlToBe(Constants.HOME_PAGE));
    }
}