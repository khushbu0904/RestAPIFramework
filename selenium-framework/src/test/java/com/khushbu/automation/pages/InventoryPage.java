package com.khushbu.automation.pages;

import com.khushbu.automation.driver.DriverFactory;
import com.khushbu.automation.utils.WaitUtils;
import org.openqa.selenium.By;

public class InventoryPage {
    private final By title = By.cssSelector(".title");
    private final By firstAddButton = By.cssSelector("[data-test^='add-to-cart-']");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    public String title() { return WaitUtils.visible(title).getText(); }
    public InventoryPage addFirstProductToCart() { WaitUtils.clickable(firstAddButton).click(); return this; }
    public String cartCount() { return WaitUtils.visible(cartBadge).getText(); }
    public void openCart() { WaitUtils.clickable(cartLink).click(); }
}