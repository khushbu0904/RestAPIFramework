package com.khushbu.automation.pages;

import com.khushbu.automation.config.ConfigManager;
import com.khushbu.automation.driver.DriverFactory;
import com.khushbu.automation.utils.WaitUtils;
import org.openqa.selenium.By;

public class LoginPage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    public LoginPage open() { DriverFactory.getDriver().get(ConfigManager.get("baseUrl", "https://www.saucedemo.com")); return this; }
    public LoginPage loginAs(String user, String pass) {
        WaitUtils.visible(username).clear(); DriverFactory.getDriver().findElement(username).sendKeys(user);
        DriverFactory.getDriver().findElement(password).clear(); DriverFactory.getDriver().findElement(password).sendKeys(pass);
        WaitUtils.clickable(loginButton).click(); return this;
    }
    public String errorMessage() { return WaitUtils.visible(errorMessage).getText(); }
}