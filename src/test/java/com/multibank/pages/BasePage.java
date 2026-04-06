package com.multibank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    protected String getText(WebElement element) {
        waitForElementVisible(element);
        return element.getText().trim();
    }

    protected boolean isElementVisible(WebElement element) {
        try {
            waitForElementVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}