# selenium-ui-java

Selenium 4 + Java 21 + JUnit 5 UI test framework for OrangeHRM Demo.

## Tech Stack

| Component | Version |
|---|---|
| Java | 21 |
| Maven | 3.9+ |
| Selenium | 4.21.0 |
| WebDriverManager | 5.8.0 |
| JUnit Jupiter | 5.10.2 |
| Allure | 2.27.0 |
| AssertJ | 3.25.3 |
| Log4j2 | 2.23.1 |

## Prerequisites

- Java 21 (temurin or equivalent)
- Maven 3.9+
- Google Chrome (latest stable)

## Running Tests

### Smoke Tests (fast, critical path)
```bash
mvn test -Dgroups=smoke
```

### Regression Tests (full suite)
```bash
mvn test -Dgroups=regression
```

### All Tests
```bash
mvn test
```

### Headed mode (local debugging)
```bash
BROWSER_HEADLESS=false mvn test -Dgroups=smoke
```

## Allure Report

Generate and serve the report after running tests:

```bash
mvn allure:serve
```

Or generate a static report:

```bash
mvn allure:report
open target/site/allure-maven-plugin/index.html
```

## Architecture

### ThreadLocal DriverManager

`DriverManager` uses a `ThreadLocal<WebDriver>` to ensure each parallel test thread gets its own isolated browser instance. The `createDriver()` method sets up ChromeDriver via WebDriverManager, applies headless/window-size options from environment config, and stores the instance in the thread-local holder. `quitDriver()` is called in `BaseTest.tearDown()` and always removes the reference to prevent memory leaks.

### Page Object Model

All pages extend `BasePage`, which wraps `WebDriverWait` for all interactions — no raw `findElement` calls in page classes. Each public method is annotated with `@Step` for Allure traceability. Locators are `private static final By` constants at the top of each class.

```
BasePage
├── LoginPage          — authentication flows
├── DashboardPage      — breadcrumb, widget count, user dropdown
├── EmployeeListPage   — search, add navigation, row count
└── AddEmployeePage    — form fill and save
```

### EmployeeBuilder (Builder Pattern)

`EmployeeBuilder` generates unique test data at construction time using a UUID suffix for the last name, preventing collisions during parallel runs. `TestDataFactory` provides static convenience methods consumed by test classes.

```java
Employee e = EmployeeBuilder.create()
    .withFirstName("Jane")
    .build();
```

### SideMenuComponent

`SideMenuComponent` is a reusable component (not a page) that encapsulates side-panel navigation via XPath targeting `nav.oxd-sidepanel`. It is injected into test classes after login.

## Configuration

All settings are environment-variable driven with sensible defaults:

| Variable | Default | Description |
|---|---|---|
| `APP_BASE_URL` | `https://opensource-demo.orangehrmlive.com` | Target application URL |
| `BROWSER_HEADLESS` | `true` | Run Chrome headless |
| `IMPLICIT_WAIT` | `10` | Implicit wait in seconds |
| `PAGE_LOAD_TIMEOUT` | `30` | Page load timeout in seconds |
| `ADMIN_USERNAME` | `Admin` | Login username |
| `ADMIN_PASSWORD` | `admin123` | Login password |

## Test Coverage

| Suite | Tag | Tests | Description |
|---|---|---|---|
| LoginTest | smoke/regression | 5 | Login form, successful login, invalid credentials, empty submit |
| DashboardTest | smoke/regression | 3 | Dashboard visible, user dropdown, widget count |
| NavigationTest | smoke/regression | 4 | PIM, Leave, Recruitment, My Info side-menu navigation |
| EmployeeTest | smoke/regression | 5 | Employee list load, search, add navigation, add new employee |
| **Total** | | **17** | |

### Tag Breakdown

- `smoke` (5 tests): Login form visible, successful login, dashboard visible, PIM navigation, employee list loads
- `regression` (12 tests): All remaining tests including invalid credentials, dropdowns, widget count, cross-module navigation, employee CRUD

## CI/CD

GitHub Actions workflow (`.github/workflows/ci.yml`) runs three sequential jobs:

1. **Build & Compile** — `mvn compile test-compile -q` (no browser needed)
2. **Smoke Tests** — `mvn test -Dgroups=smoke` (headless Chrome)
3. **Regression Tests** — `mvn test -Dgroups=regression` (headless Chrome, depends on smoke passing)

Allure results are uploaded as artifacts after each test job. Screenshots are uploaded on failure.

## Project Structure

```
selenium-ui-java/
├── .github/workflows/ci.yml
├── .gitignore
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── resources/log4j2.xml
    │   └── java/com/orangehrm/
    │       ├── config/TestConfig.java
    │       ├── driver/DriverManager.java
    │       ├── pages/BasePage.java
    │       ├── pages/LoginPage.java
    │       ├── pages/DashboardPage.java
    │       ├── pages/EmployeeListPage.java
    │       ├── pages/AddEmployeePage.java
    │       ├── components/SideMenuComponent.java
    │       └── data/
    │           ├── Employee.java
    │           ├── EmployeeBuilder.java
    │           └── TestDataFactory.java
    └── test/
        ├── resources/allure.properties
        └── java/com/orangehrm/
            ├── base/BaseTest.java
            └── tests/
                ├── LoginTest.java
                ├── DashboardTest.java
                ├── NavigationTest.java
                └── EmployeeTest.java
```
