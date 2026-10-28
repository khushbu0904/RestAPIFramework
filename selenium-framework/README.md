# Scalable Selenium + Java Test Automation Framework

A layered, extensible UI automation framework using Java 17, Selenium 4, Maven and TestNG. Demo application: [SauceDemo](https://www.saucedemo.com/) (public practice storefront; use only its provided demo accounts).

## Architecture
- **Tests**: scenario-focused TestNG classes.
- **Pages**: Page Object Model; locators and UI actions stay out of tests.
- **DriverFactory**: ThreadLocal WebDriver supports safe parallel sessions.
- **ConfigManager**: properties with system-property/environment overrides.
- **Test data**: `TestDataSource` abstraction with JSON and CSV implementations; select a source through `data.source`. Add a database/API source by implementing the interface.
- **Diagnostics**: TestNG listener saves screenshots on failure.
- **CI**: GitHub Actions installs Java 17 and runs Maven tests headlessly.

## Prerequisites
- JDK 17+
- Maven 3.9+
- Chrome/Firefox/Edge installed for local runs (Selenium Manager resolves drivers automatically).

## Run
From this directory:
```bash
mvn clean test
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge -Dheadless=true
mvn clean test -DbaseUrl=https://www.saucedemo.com
mvn clean test -Ddata.source=csv
```
Browser options: `chrome`, `firefox`, `edge`. Defaults are Chrome and headless mode enabled for CI-friendly execution. Set `-Dheadless=false` to view the browser.

## Configuration precedence
JVM system property > environment variable (uppercase, dots replaced by underscores) > `src/test/resources/config.properties`.
Example: `-DbaseUrl=https://www.saucedemo.com`.

## Data strategy
JSON: `src/test/resources/testdata/login-users.json`; CSV: `src/test/resources/testdata/login-users.csv`.
Both map records to the same typed `LoginData` model. To add another source:
1. Implement `TestDataSource`.
2. Register it in `TestDataSourceFactory`.
3. Set `data.source` or pass `-Ddata.source=yourSource`.
Keep secrets out of committed test data; use CI secrets for real environments.

## Parallel execution
The TestNG suite enables method-level parallelism. Each test owns a WebDriver via ThreadLocal and quits it in teardown. Increase/decrease `thread-count` in `testng.xml` to suit the environment.

## Reports and troubleshooting
- TestNG/Surefire output: `target/surefire-reports/`
- Failure screenshots: `target/screenshots/`
- If a browser cannot launch locally, verify it is installed and rerun without headless mode.
- Public demo sites can change or rate-limit; the CI workflow is a runnable baseline, not a guarantee of third-party uptime.

## Extending
Add page objects under `pages`, reusable widgets under `components`, tests under `tests`, and source implementations under `data`. Prefer explicit waits, stable locators, deterministic data, and isolated tests. Do not use this sample against real accounts or place real orders.