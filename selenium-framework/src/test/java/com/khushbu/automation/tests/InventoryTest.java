package com.khushbu.automation.tests;

import com.khushbu.automation.pages.InventoryPage;
import com.khushbu.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {
    @Test
    public void userCanAddProductToCart() {
        new LoginPage().open().loginAs("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage();
        Assert.assertEquals(inventory.title(), "Products");
        inventory.addFirstProductToCart();
        Assert.assertEquals(inventory.cartCount(), "1");
    }
}