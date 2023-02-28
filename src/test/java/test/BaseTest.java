package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.*;
import util.Constants;
import util.retry.Retry;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public ChromeDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        for (ITestNGMethod method : context.getSuite().getAllMethods()) {
            method.setRetryAnalyzerClass(Retry.class);
        }
    }

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
    public void tearDown(ITestResult result) throws IOException {

        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./screenshots/" + result.getName() + ".png"));
        }

        IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer(result);
        if (retry == null) {
            return;
        }

        if (result.getStatus() == 1) {
            result.getTestContext().getSkippedTests().removeResult(result.getMethod());
            result.getTestContext().getFailedTests().removeResult(result.getMethod());
        }

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