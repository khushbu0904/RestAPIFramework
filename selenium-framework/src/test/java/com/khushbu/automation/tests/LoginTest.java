package com.khushbu.automation.tests;

import com.khushbu.automation.data.LoginData;
import com.khushbu.automation.data.TestDataSourceFactory;
import com.khushbu.automation.pages.InventoryPage;
import com.khushbu.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @DataProvider(name = "loginUsers", parallel = true)
    public Object[][] loginUsers() {
        var rows = TestDataSourceFactory.loginDataSource().load();
        return rows.stream().map(row -> new Object[]{row}).toArray(Object[][]::new);
    }
    @Test(dataProvider = "loginUsers")
    public void loginOutcomeMatchesData(LoginData data) {
        LoginPage login = new LoginPage().open().loginAs(data.username(), data.password());
        if ("success".equalsIgnoreCase(data.expectedOutcome())) {
            Assert.assertEquals(new InventoryPage().title(), "Products");
        } else {
            Assert.assertFalse(login.errorMessage().isBlank(), "Expected an error message for invalid login");
        }
    }
}