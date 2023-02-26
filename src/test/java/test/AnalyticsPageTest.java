package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.AnalyticsPage;
import util.Constants;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class AnalyticsPageTest extends BaseTest {

    @Test
    public void elementControls_onAnalyticsPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        AnalyticsPage analyticsPage = new AnalyticsPage(driver, wait);

        goToAnalyticsPage();
        analyticsPage.elementDisplayed(analyticsPage.backButton);

        assertEquals(driver.findElement(analyticsPage.privacyLink).getAttribute("href"), Constants.PRIVACY_LINK);
        assertEquals(driver.findElement(analyticsPage.backButton).getText(), "No thanks");
        assertEquals(driver.findElement(analyticsPage.approveButton).getText(), "Share data");
    }

    @Test
    public void shareDataButton_shouldNavigateTo_confirmPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        AnalyticsPage analyticsPage = new AnalyticsPage(driver, wait);

        goToAnalyticsPage();
        analyticsPage.click(analyticsPage.approveButton);
        wait.until(ExpectedConditions.urlToBe(Constants.CONFIRM_PAGE));
    }

    @Test
    public void noThanksButton_shouldNavigateTo_confirmPage() throws InterruptedException, IOException, UnsupportedFlavorException {
        AnalyticsPage analyticsPage = new AnalyticsPage(driver, wait);

        goToAnalyticsPage();
        analyticsPage.click(analyticsPage.backButton);
        wait.until(ExpectedConditions.urlToBe(Constants.CONFIRM_PAGE));
    }
}