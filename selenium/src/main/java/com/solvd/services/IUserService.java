package com.solvd.services;

import com.solvd.pages.LoginPage;
import com.solvd.pages.UserPage;
import org.openqa.selenium.WebDriver;

public interface IUserService {
    default UserPage goUserPage(WebDriver driver, String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        UserPage userPage = new UserPage(driver);

        return userPage;
    }
}
