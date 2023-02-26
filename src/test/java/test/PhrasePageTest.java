package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.PhrasePage;
import util.Constants;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class PhrasePageTest extends BaseTest {

    @Test
    public void backButton_shouldNavigateTo_phraseInitialPage() throws InterruptedException {
        PhrasePage phrasePage = new PhrasePage(driver, wait);

        goToPhrasePage();
        phrasePage.click(phrasePage.backButton);
        wait.until(ExpectedConditions.urlToBe(Constants.PHRASE_INITIATE_PAGE));
    }

    @Test
    public void phraseShouldBeAppeared_viaApproveButton() throws InterruptedException {
        PhrasePage phrasePage = new PhrasePage(driver, wait);

        goToPhrasePage();
        phrasePage.elementDisplayed(phrasePage.blurredPhrase);
        phrasePage.click(phrasePage.approveButton);
        phrasePage.elementDisplayed(phrasePage.nonBlurredPhrase);
    }

    @Test
    public void copyButtonShouldWork() throws InterruptedException, IOException, UnsupportedFlavorException {
        PhrasePage phrasePage = new PhrasePage(driver, wait);

        goToPhrasePage();
        phrasePage.click(phrasePage.approveButton);
        phrasePage.click(phrasePage.copyPhraseButton);

        String myPhrase = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        assertThat(myPhrase).isNotEmpty();
    }

    @Test
    public void buttonText_shouldBeProceed() throws InterruptedException {
        PhrasePage phrasePage = new PhrasePage(driver, wait);

        goToPhrasePage();
        phrasePage.elementDisplayed(phrasePage.approveButton);
        assertEquals(driver.findElement(phrasePage.approveButton).getText(), "Got it");
        phrasePage.click(phrasePage.approveButton);
        wait.until(ExpectedConditions.textToBe(phrasePage.approveButton, "Proceed"));
    }

    @Test
    public void proceedButton_shouldNavigateTo_verifyPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        goToVerifyPage();
    }
}