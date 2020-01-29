package com.solvd.services;

import com.solvd.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public interface ILoginService {
    default LoginPage goLoginPage(WebDriver driver) {
        LoginPage page = new LoginPage(driver);
        return page;
    }
}
