package com.khushbu.automation.driver;

import com.khushbu.automation.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory() {}
    public static void createDriver() {
        if (DRIVER.get() != null) return;
        String browser = ConfigManager.get("browser", "chrome").toLowerCase();
        boolean headless = ConfigManager.getBoolean("headless", true);
        WebDriver driver;
        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--window-size=1440,1000", "--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) options.addArguments("-headless");
                driver = new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--window-size=1440,1000");
                driver = new EdgeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser '" + browser + "'. Use chrome, firefox or edge.");
        }
        DRIVER.set(driver);
    }
    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) throw new IllegalStateException("WebDriver not initialized. Call createDriver first.");
        return driver;
    }
    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        try { if (driver != null) driver.quit(); } finally { DRIVER.remove(); }
    }
}