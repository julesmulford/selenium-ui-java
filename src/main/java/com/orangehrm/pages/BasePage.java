package com.orangehrm.pages;

import com.orangehrm.config.TestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(BasePage.class);
    private static final int DEFAULT_TIMEOUT = 15;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    protected void navigateTo(String path) {
        driver.get(TestConfig.getBaseUrl() + path);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void fill(By locator, String text) {
        WebElement el = waitForClickable(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) { return false; }
    }

    protected void waitForUrlContains(String fragment) {
        wait.until(ExpectedConditions.urlContains(fragment));
    }
}
