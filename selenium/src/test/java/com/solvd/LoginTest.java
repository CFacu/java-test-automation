package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.LoginPage;
import com.solvd.services.ILoginService;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest implements ILoginService {
    @Test
    public void loginTest() {
        LoginPage page = goLoginPage(getDriver());
        page.login("Admin", "admin123");
        Assert.assertEquals("Welcome Admin", getDriver().findElement(By.id("welcome")).getText());
    }

    @AfterMethod
    public void afterMethod() {
        getDriver().quit();
    }
}
