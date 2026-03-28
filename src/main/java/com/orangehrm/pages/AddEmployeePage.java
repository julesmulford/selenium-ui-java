package com.orangehrm.pages;

import com.orangehrm.data.Employee;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage extends BasePage {
    private static final By FIRST_NAME = By.cssSelector("input[name=\"firstName\"]");
    private static final By MIDDLE_NAME = By.cssSelector("input[name=\"middleName\"]");
    private static final By LAST_NAME = By.cssSelector("input[name=\"lastName\"]");
    private static final By SAVE_BUTTON = By.cssSelector("button[type=\"submit\"]");

    public AddEmployeePage(WebDriver driver) { super(driver); }

    @Step("Assert on add employee page")
    public void assertOnPage() { waitForUrlContains("/pim/addEmployee"); }

    @Step("Fill employee details")
    public void fillEmployeeDetails(Employee employee) {
        fill(FIRST_NAME, employee.firstName());
        fill(MIDDLE_NAME, employee.middleName());
        fill(LAST_NAME, employee.lastName());
    }

    @Step("Save employee")
    public void save() { click(SAVE_BUTTON); }
}
