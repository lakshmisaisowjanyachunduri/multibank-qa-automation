package com.multibank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends BasePage {

    @FindBy(css = "input[type='email'], input[name='email'], input[placeholder*='Email']")
    private WebElement emailField;

    @FindBy(css = "input[type='password'], input[name='password'], input[placeholder*='Password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit'], button.login-btn, .login-button")
    private WebElement loginButton;

    @FindBy(css = "a[href*='forgot'], a[href*='reset']")
    private WebElement forgotPasswordLink;

    @FindBy(css = "a[href*='register'], a[href*='signup'], a[href*='sign-up']")
    private WebElement signUpLink;

    @FindBy(css = "label, placeholder, input")
    private List<WebElement> formElements;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isEmailFieldDisplayed() {
        return isElementVisible(emailField);
    }

    public boolean isPasswordFieldDisplayed() {
        return isElementVisible(passwordField);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementVisible(loginButton);
    }

    public boolean isForgotPasswordLinkDisplayed() {
        try {
            return forgotPasswordLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignUpLinkDisplayed() {
        try {
            return signUpLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmail(String email) {
        waitForElementVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        waitForElementVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        waitForElementClickable(loginButton);
        loginButton.click();
    }

    public String getEmailFieldPlaceholder() {
        waitForElementVisible(emailField);
        return emailField.getAttribute("placeholder");
    }

    public String getPasswordFieldPlaceholder() {
        waitForElementVisible(passwordField);
        return passwordField.getAttribute("placeholder");
    }

    public boolean isEmailFieldEnabled() {
        return emailField.isEnabled();
    }

    public boolean isPasswordFieldEnabled() {
        return passwordField.isEnabled();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}