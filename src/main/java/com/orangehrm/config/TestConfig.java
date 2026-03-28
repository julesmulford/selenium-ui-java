package com.orangehrm.config;

public final class TestConfig {
    private TestConfig() {}

    public static String getBaseUrl() {
        return System.getenv().getOrDefault("APP_BASE_URL", "https://opensource-demo.orangehrmlive.com");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getenv().getOrDefault("BROWSER_HEADLESS", "true"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(System.getenv().getOrDefault("IMPLICIT_WAIT", "10"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(System.getenv().getOrDefault("PAGE_LOAD_TIMEOUT", "30"));
    }

    public static String getAdminUsername() {
        return System.getenv().getOrDefault("ADMIN_USERNAME", "Admin");
    }

    public static String getAdminPassword() {
        return System.getenv().getOrDefault("ADMIN_PASSWORD", "admin123");
    }
}
