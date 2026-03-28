package com.orangehrm.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SideMenuComponent {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SideMenuComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Navigate to menu item: {item}")
    public void navigateTo(String item) {
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//nav[contains(@class,'oxd-sidepanel')]//span[normalize-space()='" + item + "']")));
        menuItem.click();
    }

    public void navigateToPIM() { navigateTo("PIM"); }
    public void navigateToLeave() { navigateTo("Leave"); }
    public void navigateToRecruitment() { navigateTo("Recruitment"); }
    public void navigateToMyInfo() { navigateTo("My Info"); }
}
