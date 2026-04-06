package com.multibank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class AboutPage extends BasePage {

    private static final String WHY_MULTIBANK_URL = "https://trade.multibank.io/about-us/why-multibank/";

    @FindBy(css = "h1, h2, h3, [class*='title'], [class*='heading']")
    private List<WebElement> pageHeadings;

    @FindBy(css = "[class*='regulation'], [class*='award'], [class*='security'], [class*='why']")
    private List<WebElement> whyMultibankComponents;

    @FindBy(css = ".page-content, main, [class*='content']")
    private WebElement pageContent;

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToWhyMultibank() {
        driver.get(WHY_MULTIBANK_URL);
    }

    public List<String> getPageHeadings() {
        return pageHeadings.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getWhyMultibankComponents() {
        return whyMultibankComponents.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isPageContentDisplayed() {
        return isElementVisible(pageContent);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}