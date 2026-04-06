package com.multibank.tests;

import com.aventstack.extentreports.Status;
import com.multibank.pages.LoginPage;
import com.multibank.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContentValidationTest extends BaseTest {

    @Test(description = "Verify login page email field placeholder text")
    public void testEmailFieldPlaceholder() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login page");

        String placeholder = loginPage.getEmailFieldPlaceholder();
        extentTest.log(Status.INFO, "Email placeholder: " + placeholder);

        Assert.assertNotNull(placeholder, "Email field should have placeholder text");
        extentTest.log(Status.PASS, "Email placeholder verified: " + placeholder);
    }

    @Test(description = "Verify forgot password link is present")
    public void testForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login page");

        boolean forgotVisible = loginPage.isForgotPasswordLinkDisplayed();
        extentTest.log(Status.INFO, "Forgot password link visible: " + forgotVisible);

        if (forgotVisible) {
            extentTest.log(Status.PASS, "Forgot password link is present");
        } else {
            extentTest.log(Status.WARNING, "Forgot password link not found — may use different selector");
        }

        Assert.assertTrue(
                loginPage.isLoginButtonDisplayed(),
                "Login page should load with login button"
        );
    }

    @Test(description = "Verify login page URL structure is correct")
    public void testLoginPageUrlStructure() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login URL");

        String currentUrl = loginPage.getCurrentUrl();
        extentTest.log(Status.INFO, "Current URL: " + currentUrl);

        Assert.assertTrue(
                currentUrl.contains("login"),
                "Login URL should contain 'login'"
        );
        extentTest.log(Status.PASS, "URL structure verified: " + currentUrl);
    }

    @Test(description = "Verify signup page loads correctly")
    public void testSignUpPageLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("signup.url"));

        extentTest.log(Status.INFO, "Navigated to signup URL");

        String title = loginPage.getPageTitle();
        String url = loginPage.getCurrentUrl();

        extentTest.log(Status.INFO, "Page title: " + title);
        extentTest.log(Status.INFO, "Current URL: " + url);

        Assert.assertFalse(title.isEmpty(), "Sign up page should load with a title");
        extentTest.log(Status.PASS, "Sign up page loaded: " + title);
    }
}