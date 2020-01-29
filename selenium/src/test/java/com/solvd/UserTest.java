package com.solvd;

import com.solvd.pages.UserPage;
import com.solvd.services.IUserService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UserTest extends AbstractTest implements IUserService {

    @Test
    public void addNewUserTest() {
        UserPage userPage = goUserPage(getDriver(), "Admin", "admin123");
        userPage.addUser("ESS", "Hannah Flores", "HannaF", "1");
        Assert.assertTrue(userPage.userExists("HannaF"));
    }

    @AfterMethod
    public void afterMethod() {
        getDriver().quit();
    }

}
