package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.data.TestDataFactory;
import com.orangehrm.pages.LoginPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Authentication")
class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    void navigateToLogin() {
        loginPage = new LoginPage(driver);
        loginPage.navigate();
    }

    @Test
    @Tag("smoke")
    void testLoginFormVisible() {
        loginPage.assertOnLoginPage();
    }

    @Test
    @Tag("smoke")
    void testSuccessfulLogin() {
        loginPage.login(TestDataFactory.adminUsername(), TestDataFactory.adminPassword());
        loginPage.assertLoggedIn();
    }

    @Test
    @Tag("regression")
    void testInvalidPassword() {
        loginPage.login(TestDataFactory.adminUsername(), TestDataFactory.invalidPassword());
        loginPage.assertErrorVisible();
    }

    @Test
    @Tag("regression")
    void testInvalidCredentials() {
        loginPage.login(TestDataFactory.invalidUsername(), TestDataFactory.invalidPassword());
        loginPage.assertErrorVisible();
    }

    @Test
    @Tag("regression")
    void testEmptySubmit() {
        loginPage.clickSubmit();
        loginPage.assertOnLoginPage();
    }
}
