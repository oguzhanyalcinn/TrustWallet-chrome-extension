package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.ConfirmPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ConfirmPageTest extends BaseTest {

    @Test
    public void elementControls_onConfirmPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        ConfirmPage confirmPage = new ConfirmPage(driver, wait);

        goToAnalyticsPage();
        confirmPage.click(confirmPage.approveButton);

        confirmPage.elementDisplayed(confirmPage.openWalletButton);
        confirmPage.elementDisplayed(confirmPage.confirmText);
        assertEquals(driver.findElement(confirmPage.confirmText).getText(), "Your wallet has been created!");
        confirmPage.elementDisplayed(confirmPage.checkedSwitch);

        confirmPage.click(confirmPage.checkedSwitch);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmPage.checkedSwitch));
    }

    @Test
    public void openWalletButton_shouldNavigateTo_homePage() throws InterruptedException, IOException, UnsupportedFlavorException {
        goToHomePage();
    }
}