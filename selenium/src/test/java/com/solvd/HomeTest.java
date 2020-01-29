package com.solvd;

import com.solvd.pages.HomePage;
import com.solvd.services.IHomeService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class HomeTest extends AbstractTest implements IHomeService {

    @Test
    public void verifyHomePage() {
        HomePage home = goHomePage(getDriver(), "Admin", "admin123");
        Assert.assertEquals("Admin", home.getAdminHyperlink().getText(), "Tab Admin not found");
        Assert.assertEquals("PIM", home.getPimHyperLink().getText());
    }

    @AfterMethod
    public void afterMethod() {
        getDriver().quit();
    }

}
