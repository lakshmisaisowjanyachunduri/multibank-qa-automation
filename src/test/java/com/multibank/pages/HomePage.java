package com.multibank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private static final String URL = "https://trade.mb.io/";

    @FindBy(css = ".sidebar a, nav a, [class*='nav'] a, [class*='sidebar'] a")
    private List<WebElement> navigationMenuItems;

    @FindBy(css = "[class*='portfolio'], [class*='Portfolio']")
    private WebElement portfolioSection;

    @FindBy(css = "input[placeholder*='Search'], input[type='search']")
    private WebElement searchBar;

    @FindBy(css = "[class*='crypto-price'], [class*='price-table'], table, [class*='market']")
    private WebElement cryptoPriceTable;

    @FindBy(css = "a[href*='apps.apple.com'], a[href*='apple'], [class*='app-store'], [class*='qr']")
    private WebElement appStoreLink;

    @FindBy(css = "a[href*='play.google.com'], a[href*='google-play'], [class*='google-play']")
    private WebElement googlePlayLink;

    @FindBy(css = ".watchlist, [class*='watchlist'], [class*='tab']")
    private List<WebElement> marketTabs;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get(URL);
    }

    public List<String> getNavigationMenuItems() {
        return navigationMenuItems.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isPortfolioSectionDisplayed() {
        return isElementVisible(portfolioSection);
    }

    public boolean isSearchBarDisplayed() {
        return isElementVisible(searchBar);
    }

    public boolean isCryptoPriceTableDisplayed() {
        return isElementVisible(cryptoPriceTable);
    }

    public boolean isAppStoreLinkPresent() {
        try {
            return appStoreLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGooglePlayLinkPresent() {
        try {
            return googlePlayLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getMarketTabs() {
        return marketTabs.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public String getAppStoreHref() {
        waitForElementVisible(appStoreLink);
        return appStoreLink.getAttribute("href");
    }

    public String getGooglePlayHref() {
        waitForElementVisible(googlePlayLink);
        return googlePlayLink.getAttribute("href");
    }

    public boolean areMarketingBannersDisplayed() {
        return isPortfolioSectionDisplayed() || isCryptoPriceTableDisplayed();
    }
}