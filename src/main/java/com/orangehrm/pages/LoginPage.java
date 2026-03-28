package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By USERNAME = By.cssSelector("input[name=\"username\"]");
    private static final By PASSWORD = By.cssSelector("input[name=\"password\"]");
    private static final By SUBMIT = By.cssSelector("button[type=\"submit\"]");
    private static final By ERROR = By.cssSelector(".oxd-alert-content-text");
    private static final By REQUIRED_ERROR = By.cssSelector(".oxd-input-field-error-message");

    public LoginPage(WebDriver driver) { super(driver); }

    @Step("Navigate to login page")
    public void navigate() { navigateTo("/web/index.php/auth/login"); }

    @Step("Login with username: {username}")
    public void login(String username, String password) {
        fill(USERNAME, username);
        fill(PASSWORD, password);
        click(SUBMIT);
    }

    @Step("Click submit")
    public void clickSubmit() { click(SUBMIT); }

    @Step("Assert logged in")
    public void assertLoggedIn() { waitForUrlContains("/dashboard/index"); }

    @Step("Assert error visible")
    public void assertErrorVisible() {
        assert isVisible(ERROR) : "Error message should be visible";
    }

    @Step("Assert on login page")
    public void assertOnLoginPage() {
        assert isVisible(SUBMIT) : "Submit button should be visible";
    }
}
