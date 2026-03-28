package com.orangehrm.base;

import com.orangehrm.config.TestConfig;
import com.orangehrm.driver.DriverManager;
import com.orangehrm.pages.LoginPage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        driver = DriverManager.createDriver();
        Allure.parameter("Test", testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Final Screenshot - " + testInfo.getDisplayName(),
                        new ByteArrayInputStream(screenshot));
            } catch (Exception ignored) {}
        }
        DriverManager.quitDriver();
    }

    protected void loginAsAdmin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login(TestConfig.getAdminUsername(), TestConfig.getAdminPassword());
        loginPage.assertLoggedIn();
    }
}
