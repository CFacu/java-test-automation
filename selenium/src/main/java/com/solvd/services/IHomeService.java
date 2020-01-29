package com.solvd.services;

import com.solvd.pages.HomePage;
import com.solvd.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public interface IHomeService {
    default HomePage goHomePage(WebDriver driver, String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        HomePage home = new HomePage(driver);
        return home;
    }
}
