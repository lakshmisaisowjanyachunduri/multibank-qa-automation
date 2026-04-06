package com.multibank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TradingPage extends BasePage {

    @FindBy(css = ".trading-pair, [class*='pair'], [class*='instrument'], [class*='symbol']")
    private List<WebElement> tradingPairs;

    @FindBy(css = ".category-tab, [class*='category'], [class*='tab'], .market-tab")
    private List<WebElement> tradingCategories;

    @FindBy(css = "[class*='spot'], [class*='trading-section'], .spot-trading")
    private WebElement spotTradingSection;

    public TradingPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getTradingCategories() {
        return tradingCategories.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getTradingPairs() {
        return tradingPairs.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isSpotTradingSectionDisplayed() {
        try {
            return isElementVisible(spotTradingSection);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasTradingPairs() {
        return !tradingPairs.isEmpty();
    }
}