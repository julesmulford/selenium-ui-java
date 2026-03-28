package com.orangehrm.data;

public final class TestDataFactory {
    private TestDataFactory() {}

    public static Employee createEmployee() { return EmployeeBuilder.create().build(); }
    public static String adminUsername() { return "Admin"; }
    public static String adminPassword() { return "admin123"; }
    public static String invalidPassword() { return "wrongpassword"; }
    public static String invalidUsername() { return "invalid_user"; }
}
