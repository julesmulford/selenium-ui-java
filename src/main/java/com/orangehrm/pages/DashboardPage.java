package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private static final By BREADCRUMB = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private static final By USER_DROPDOWN = By.cssSelector(".oxd-userdropdown-tab");
    private static final By USER_DROPDOWN_MENU = By.cssSelector(".oxd-userdropdown-dropdown");
    private static final By WIDGETS = By.cssSelector(".oxd-grid-item");

    public DashboardPage(WebDriver driver) { super(driver); }

    @Step("Assert on dashboard")
    public void assertOnDashboard() {
        waitForUrlContains("/dashboard/index");
        String title = getText(BREADCRUMB);
        assert title.contains("Dashboard") : "Expected Dashboard in title, got: " + title;
    }

    @Step("Open user dropdown")
    public void openUserDropdown() { click(USER_DROPDOWN); }

    @Step("Assert user dropdown visible")
    public void assertUserDropdownVisible() {
        openUserDropdown();
        assert isVisible(USER_DROPDOWN_MENU) : "Dropdown menu should be visible";
    }

    public int getWidgetCount() { return driver.findElements(WIDGETS).size(); }
}
