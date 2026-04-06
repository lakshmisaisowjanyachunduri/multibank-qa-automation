package com.multibank.tests;

import com.aventstack.extentreports.Status;
import com.multibank.pages.LoginPage;
import com.multibank.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TradingTest extends BaseTest {

    @Test(description = "Verify trading platform login page loads correctly")
    public void testTradingPlatformLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("base.url"));

        extentTest.log(Status.INFO, "Navigated to trading platform");

        String url = loginPage.getCurrentUrl();
        String title = loginPage.getPageTitle();

        extentTest.log(Status.INFO, "URL: " + url);
        extentTest.log(Status.INFO, "Title: " + title);

        Assert.assertTrue(
                url.contains("trade.mb.io"),
                "Trading platform URL should be trade.mb.io"
        );
        extentTest.log(Status.PASS, "Trading platform loaded: " + url);
    }

    @Test(description = "Verify login form is present on trading platform")
    public void testLoginFormPresent() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login page");

        boolean emailVisible = loginPage.isEmailFieldDisplayed();
        boolean passwordVisible = loginPage.isPasswordFieldDisplayed();
        boolean loginBtnVisible = loginPage.isLoginButtonDisplayed();

        extentTest.log(Status.INFO, "Email field visible: " + emailVisible);
        extentTest.log(Status.INFO, "Password field visible: " + passwordVisible);
        extentTest.log(Status.INFO, "Login button visible: " + loginBtnVisible);

        Assert.assertTrue(emailVisible, "Email field should be visible");
        Assert.assertTrue(passwordVisible, "Password field should be visible");
        Assert.assertTrue(loginBtnVisible, "Login button should be visible");

        extentTest.log(Status.PASS, "All login form elements are present");
    }

    @Test(description = "Verify email field accepts input")
    public void testEmailFieldAcceptsInput() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login page");

        Assert.assertTrue(
                loginPage.isEmailFieldEnabled(),
                "Email field should be enabled and accept input"
        );

        loginPage.enterEmail("test@example.com");
        extentTest.log(Status.PASS, "Email field accepts input correctly");
    }

    @Test(description = "Verify password field accepts input")
    public void testPasswordFieldAcceptsInput() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(ConfigReader.get("login.url"));

        extentTest.log(Status.INFO, "Navigated to login page");

        Assert.assertTrue(
                loginPage.isPasswordFieldEnabled(),
                "Password field should be enabled and accept input"
        );

        loginPage.enterPassword("TestPassword123");
        extentTest.log(Status.PASS, "Password field accepts input correctly");
    }
}