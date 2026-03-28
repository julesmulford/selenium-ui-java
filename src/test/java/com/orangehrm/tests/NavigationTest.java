package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.components.SideMenuComponent;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Navigation")
class NavigationTest extends BaseTest {
    private SideMenuComponent sideMenu;

    @BeforeEach
    void login() {
        loginAsAdmin();
        sideMenu = new SideMenuComponent(driver);
    }

    @Test
    @Tag("smoke")
    void testNavigateToPIM() {
        sideMenu.navigateToPIM();
        Assertions.assertThat(driver.getCurrentUrl()).contains("/pim/");
    }

    @Test
    @Tag("regression")
    void testNavigateToLeave() {
        sideMenu.navigateToLeave();
        Assertions.assertThat(driver.getCurrentUrl()).contains("/leave/");
    }

    @Test
    @Tag("regression")
    void testNavigateToRecruitment() {
        sideMenu.navigateToRecruitment();
        Assertions.assertThat(driver.getCurrentUrl()).contains("/recruitment/");
    }

    @Test
    @Tag("regression")
    void testNavigateToMyInfo() {
        sideMenu.navigateToMyInfo();
        Assertions.assertThat(driver.getCurrentUrl()).contains("/pim/viewMyDetails");
    }
}
