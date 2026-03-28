package com.orangehrm.driver;

import com.orangehrm.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public final class DriverManager {
    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (TestConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1280,720", "--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConfig.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConfig.getPageLoadTimeout()));
        driverHolder.set(driver);
        log.info("ChromeDriver created (headless={})", TestConfig.isHeadless());
        return driver;
    }

    public static WebDriver getDriver() { return driverHolder.get(); }

    public static void quitDriver() {
        WebDriver driver = driverHolder.get();
        if (driver != null) {
            try { driver.quit(); } catch (Exception e) { log.warn("Quit error: {}", e.getMessage()); }
            finally { driverHolder.remove(); }
        }
    }
}
