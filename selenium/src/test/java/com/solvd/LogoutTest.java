package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.pages.LoginPage;
import com.solvd.services.IHomeService;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LogoutTest extends AbstractTest implements IHomeService {
    @Test
    public void logoutTest() {
        HomePage home = new HomePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123");
        home.logout();
        Assert.assertEquals("LOGIN Panel", getDriver().findElement(By.id("logInPanelHeading")).getText());
    }

    @AfterMethod
    public void afterMethod() {
        getDriver().quit();
    }
}
