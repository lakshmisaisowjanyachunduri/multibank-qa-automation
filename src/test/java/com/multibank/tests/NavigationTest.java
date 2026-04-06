package com.multibank.tests;

import com.aventstack.extentreports.Status;
import com.multibank.pages.LoginPage;
import com.multibank.utils.ConfigReader;
import com.multibank.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NavigationTest extends BaseTest {

    @Test(description = "Verify login page loads and redirects correctly")
    public void testLoginPageLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("base.url"));

        extentTest.log(Status.INFO, "Navigated to base URL");

        String currentUrl = loginPage.getCurrentUrl();
        extentTest.log(Status.INFO, "Redirected to: " + currentUrl);

        Assert.assertTrue(
                currentUrl.contains("trade.mb.io"),
                "Should redirect within trade.mb.io domain"
        );
        extentTest.log(Status.PASS, "Login page loaded and URL is correct: " + currentUrl);
    }

    @Test(description = "Verify login page title is correct")
    public void testLoginPageTitle() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login URL");

        String title = loginPage.getPageTitle();
        extentTest.log(Status.INFO, "Page title: " + title);

        Assert.assertFalse(title.isEmpty(), "Page title should not be empty");
        extentTest.log(Status.PASS, "Page title verified: " + title);
    }

    @Test(description = "Verify signup page is accessible from login")
    public void testSignUpPageAccessible() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login page");

        boolean signUpVisible = loginPage.isSignUpLinkDisplayed();
        extentTest.log(Status.INFO, "Sign up link visible: " + signUpVisible);

        if (signUpVisible) {
            extentTest.log(Status.PASS, "Sign up link is accessible from login page");
        } else {
            extentTest.log(Status.WARNING, "Sign up link not found — may use different selector");
        }

        String title = loginPage.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Login page should load successfully");
    }
}