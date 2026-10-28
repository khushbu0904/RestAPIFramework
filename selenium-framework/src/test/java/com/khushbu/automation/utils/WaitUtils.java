package com.khushbu.automation.utils;

import com.khushbu.automation.config.ConfigManager;
import com.khushbu.automation.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public final class WaitUtils {
    private WaitUtils() {}
    private static WebDriverWait waitFor() {
        return new WebDriverWait(DriverFactory.getDriver(),
                Duration.ofSeconds(ConfigManager.getInt("explicitWaitSeconds", 10)));
    }
    public static WebElement visible(By locator) { return waitFor().until(ExpectedConditions.visibilityOfElementLocated(locator)); }
    public static WebElement clickable(By locator) { return waitFor().until(ExpectedConditions.elementToBeClickable(locator)); }
    public static boolean urlContains(String value) { return waitFor().until(ExpectedConditions.urlContains(value)); }
}