package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage extends BasePage {
    private static final By PAGE_TITLE = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private static final By SEARCH_INPUT = By.cssSelector("[placeholder=\"Type for hints...\"]");
    private static final By SEARCH_BUTTON = By.cssSelector("button[type=\"submit\"]");
    private static final By ADD_BUTTON = By.xpath("//button[normalize-space()=\"Add Employee\"]");
    private static final By TABLE_ROWS = By.cssSelector(".oxd-table-body .oxd-table-row");

    public EmployeeListPage(WebDriver driver) { super(driver); }

    @Step("Navigate to employee list")
    public void navigate() { navigateTo("/web/index.php/pim/viewEmployeeList"); }

    @Step("Assert on employee list page")
    public void assertOnPage() { waitForUrlContains("/pim/viewEmployeeList"); }

    @Step("Search by name: {name}")
    public void searchByName(String name) {
        fill(SEARCH_INPUT, name);
        click(SEARCH_BUTTON);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Click add employee")
    public void clickAddEmployee() { click(ADD_BUTTON); }

    public int getRowCount() { return driver.findElements(TABLE_ROWS).size(); }

    @Step("Assert has results")
    public void assertHasResults() {
        assert getRowCount() > 0 : "Employee list should have results";
    }
}
