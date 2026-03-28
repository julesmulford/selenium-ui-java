package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.data.TestDataFactory;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.EmployeeListPage;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Employee Management")
class EmployeeTest extends BaseTest {
    private EmployeeListPage employeeListPage;

    @BeforeEach
    void login() {
        loginAsAdmin();
        employeeListPage = new EmployeeListPage(driver);
        employeeListPage.navigate();
    }

    @Test
    @Tag("smoke")
    void testEmployeeListLoads() {
        employeeListPage.assertOnPage();
    }

    @Test
    @Tag("regression")
    void testEmployeeListHasResults() {
        employeeListPage.assertHasResults();
    }

    @Test
    @Tag("regression")
    void testSearchEmployee() {
        employeeListPage.searchByName("Admin");
        employeeListPage.assertHasResults();
    }

    @Test
    @Tag("regression")
    void testNavigateToAddEmployee() {
        employeeListPage.clickAddEmployee();
        new AddEmployeePage(driver).assertOnPage();
    }

    @Test
    @Tag("regression")
    void testAddNewEmployee() {
        var employee = TestDataFactory.createEmployee();
        employeeListPage.clickAddEmployee();
        AddEmployeePage addPage = new AddEmployeePage(driver);
        addPage.assertOnPage();
        addPage.fillEmployeeDetails(employee);
        addPage.save();
        Assertions.assertThat(driver.getCurrentUrl()).contains("/pim/viewPersonalDetails");
    }
}
