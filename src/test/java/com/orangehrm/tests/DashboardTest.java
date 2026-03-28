package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Dashboard")
class DashboardTest extends BaseTest {
    private DashboardPage dashboardPage;

    @BeforeEach
    void login() {
        loginAsAdmin();
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    @Tag("smoke")
    void testDashboardVisible() {
        dashboardPage.assertOnDashboard();
    }

    @Test
    @Tag("regression")
    void testUserDropdown() {
        dashboardPage.assertUserDropdownVisible();
    }

    @Test
    @Tag("regression")
    void testWidgetsPresent() {
        Assertions.assertThat(dashboardPage.getWidgetCount()).isGreaterThan(0);
    }
}
