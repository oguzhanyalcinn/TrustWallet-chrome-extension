package test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.OnboardingPage;
import pages.PasswordPage;
import util.Constants;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PasswordTest extends BaseTest {

    @Test
    public void proceedButton_shouldBeDisabled() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        wait.until(ExpectedConditions.urlToBe(Constants.PASSWORD_PAGE));
        passwordPage.elementDisplayed(passwordPage.disabledProceedButton);
    }

    @Test
    public void allIcons_shouldBeUnchecked() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.elementDisplayed(passwordPage.uncheckedIcons);
        List<WebElement> elements = driver.findElements(passwordPage.uncheckedIcons);
        assertEquals(elements.size(), 4);
    }

    @DataProvider(name = "password-values")
    public static Object[][] passwords() {
        return new Object[][]{
                {"123", 3, 1},
                {"123.", 2, 2},
                {"123.O", 1, 3},
                {"123.Oguzhan", 0, 4}
        };
    }

    @Test(dataProvider = "password-values")
    public void checkAllIcons_oneByOne(String password, int unchecked, int checked) {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.sendKeys(passwordPage.passwordInputBox, password);

        List<WebElement> uncheckedElements = driver.findElements(passwordPage.uncheckedIcons);
        assertEquals(uncheckedElements.size(), unchecked);

        List<WebElement> checkedElements = driver.findElements(passwordPage.checkedIcons);
        assertEquals(checkedElements.size(), checked);
    }

    @Test
    public void passwordsShouldMatch() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.sendKeys(passwordPage.passwordInputBox, "123");
        passwordPage.sendKeys(passwordPage.confirmPasswordInputBox, "qwe");
        passwordPage.elementDisplayed(passwordPage.passwordNotMatchText);
        assertEquals(driver.findElement(passwordPage.passwordNotMatchText).getText(), "Passwords do not match");
    }

    @Test
    public void passwordsShouldBeReadable_viaEyeButtons() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.click(passwordPage.passwordEyeButton);
        passwordPage.elementDisplayed(passwordPage.declassifiedPasswordInput);
        passwordPage.click(passwordPage.confirmPasswordEyeButton);
        passwordPage.elementDisplayed(passwordPage.declassifiedConfirmPasswordInput);
    }

    @Test
    public void termsHasToBeChecked() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.sendKeys(passwordPage.passwordInputBox, "123.Oguzhan");
        passwordPage.sendKeys(passwordPage.confirmPasswordInputBox, "123.Oguzhan");
        passwordPage.elementDisplayed(passwordPage.disabledProceedButton);

        passwordPage.click(passwordPage.termsCheckbox);
        assertEquals(driver.findElements(passwordPage.disabledProceedButton).size(), 0);
    }

    @Test
    public void backButton_shouldNavigateTo_onboardingPage() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        PasswordPage passwordPage = new PasswordPage(driver, wait);

        onboardingPage.click(onboardingPage.createWalletButton);
        passwordPage.click(passwordPage.backButton);
        wait.until(ExpectedConditions.urlToBe(Constants.ONBOARDING_PAGE));
    }
}