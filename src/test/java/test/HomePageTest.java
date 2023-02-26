package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.HomePage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HomePageTest extends BaseTest {

    @Test
    public void dialogTest_onHomePage() throws InterruptedException, IOException, UnsupportedFlavorException {
        HomePage homePage = new HomePage(driver, wait);

        goToHomePage();
        homePage.elementDisplayed(homePage.tipDialog);
        homePage.elementDisplayed(homePage.closeButtonOnDialog);
        homePage.elementDisplayed(homePage.gotItButtonOnDialog);

        homePage.click(homePage.gotItButtonOnDialog);
        wait.until(ExpectedConditions.textToBe(homePage.gotItButtonOnDialog, "I'm ready to use Trust Wallet!"));
    }

    @Test
    public void dialogShouldBeClosed_ifIClickOnCloseButton() throws InterruptedException, IOException, UnsupportedFlavorException {
        HomePage homePage = new HomePage(driver, wait);

        goToHomePage();
        homePage.click(homePage.closeButtonOnDialog);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(homePage.tipDialog));
    }

    @Test
    public void dialogShouldBeClosed_ifIClickOnGotItButton() throws InterruptedException, IOException, UnsupportedFlavorException {
        HomePage homePage = new HomePage(driver, wait);

        goToHomePage();
        homePage.click(homePage.gotItButtonOnDialog);
        wait.until(ExpectedConditions.textToBe(homePage.gotItButtonOnDialog, "I'm ready to use Trust Wallet!"));
        homePage.click(homePage.gotItButtonOnDialog);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(homePage.tipDialog));
    }
}