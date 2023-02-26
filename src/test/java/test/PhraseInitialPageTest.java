package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.PhraseInitialPage;
import util.Constants;

import static org.testng.Assert.assertEquals;

public class PhraseInitialPageTest extends BaseTest {

    @Test
    public void startButtonShouldBeAvailable() throws InterruptedException {
        PhraseInitialPage phraseInitialPage = new PhraseInitialPage(driver, wait);

        goToPhraseInitialPage();
        phraseInitialPage.elementDisplayed(phraseInitialPage.approveButton);
    }

    @Test
    public void bestPracticeLink_shouldBeCorrect() throws InterruptedException {
        PhraseInitialPage phraseInitialPage = new PhraseInitialPage(driver, wait);

        goToPhraseInitialPage();
        phraseInitialPage.elementDisplayed(phraseInitialPage.bestPractiseLink);
        assertEquals(driver.findElement(phraseInitialPage.bestPractiseLink).getAttribute("href"), Constants.BEST_PRACTISE_LINK);
    }

    @Test
    public void startButton_shouldNavigateTo_phrasePage() throws InterruptedException {
        PhraseInitialPage phraseInitialPage = new PhraseInitialPage(driver, wait);

        goToPhraseInitialPage();
        phraseInitialPage.click(phraseInitialPage.approveButton);
        wait.until(ExpectedConditions.urlToBe(Constants.PHRASE_PAGE));
    }
}