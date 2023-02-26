package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.VerifyPage;
import util.Constants;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class VerifyPageTest extends BaseTest {

    @Test
    public void thereShouldBe12Phrases_onVerifyPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        goToVerifyPage();
        verifyPage.elementDisplayed(verifyPage.phrases);
        assertEquals(driver.findElements(verifyPage.phrases).size(), 12);
    }

    @Test
    public void nextButtonShouldBeDisabled() throws InterruptedException, IOException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        goToVerifyPage();
        verifyPage.elementDisplayed(verifyPage.disabledApproveButton);
    }

    @Test
    public void backButton_shouldNavigateTo_phrasePage() throws InterruptedException, IOException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        goToVerifyPage();
        verifyPage.click(verifyPage.backButton);
        wait.until(ExpectedConditions.urlToBe(Constants.PHRASE_PAGE));
    }

    @Test
    public void invalidSecretPhraseTest() throws InterruptedException, IOException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        goToVerifyPage();

        for (int i = 0; i < 12; i++) {
            driver.findElements(verifyPage.phrases).get(i).click();
        }

        verifyPage.elementDisplayed(verifyPage.invalidSecretText);
        assertEquals(driver.findElement(verifyPage.invalidSecretText).getText(), "Invalid Secret Phrase");
    }

    @Test
    public void checkCrossButtonFunctionality() throws InterruptedException, IOException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        goToVerifyPage();
        verifyPage.click(verifyPage.phrases);
        assertEquals(driver.findElements(verifyPage.selectedPhrases).size(), 1);
        verifyPage.click(verifyPage.crossButton);
        assertEquals(driver.findElements(verifyPage.selectedPhrases).size(), 0);
    }

    @Test
    public void nextButtonShouldBeEnabled_whenPhrasesAreCorrect() throws InterruptedException, IOException, UnsupportedFlavorException {
        VerifyPage verifyPage = new VerifyPage(driver, wait);

        String myPhrase = goToVerifyPage();

        for (String s : myPhrase.split(" ")) {
            verifyPage.clickOnSpecificPhrase(s);
        }

        assertEquals(driver.findElements(verifyPage.selectedPhrases).size(), 12);
        assertEquals(driver.findElements(verifyPage.disabledApproveButton).size(), 0);
    }

    @Test
    public void nextButton_shouldNavigateTo_analyticsPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        goToAnalyticsPage();
        wait.until(ExpectedConditions.urlToBe(Constants.ANALYTICS_PAGE));
    }
}
