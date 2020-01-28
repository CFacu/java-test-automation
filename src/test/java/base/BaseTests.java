package base;

import com.solvd.pages.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BaseTests extends AbstractTest{
    @Test
    public void loginTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");
        Assert.assertEquals("Welcome Admin", getDriver().findElement(By.id("welcome")).getText(), "Login failed.");
        getDriver().quit();
    }

    @Test
    public void welcomeAdminTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.clickWelcome();

        Assert.assertTrue(getDriver().findElement(By.id("aboutDisplayLink")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.linkText("Logout")).isDisplayed());
        getDriver().quit();
    }

    @Test
    public void adminTabTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.clickAdmin();

        Assert.assertEquals("input", getDriver().findElement(By.id("searchBtn")).getTagName());
        getDriver().quit();
    }

    @Test
    public void leaveTabTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.clickLeave();

        Assert.assertEquals("input", getDriver().findElement(By.name("btnReset")).getTagName());
        getDriver().quit();
    }

    @Test
    public void timeTabTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.clickTime();

        Assert.assertEquals("input", getDriver().findElement(By.name("btnSave")).getTagName());
        getDriver().quit();
    }

    @Test
    public void searchUsernameTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");

        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.clickAdmin();

        AdminPage adminPage = PageFactory.initElements(getDriver(), AdminPage.class);
        adminPage.searchUsername("Admin");

        Assert.assertEquals("Admin", getDriver().findElement(By.linkText("Admin")).getText());

        getDriver().quit();
    }

    @Test
    public void addEmployeeTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.login("Admin", "admin123");

        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.clickPim();

        PimPage pimPage = PageFactory.initElements(getDriver(), PimPage.class);
        pimPage.clickAddEmployee();

        AddEmployeePage addEmployeePage = PageFactory.initElements(getDriver(), AddEmployeePage.class);
        addEmployeePage.addEmployee("Facundo", "Costa");



        getDriver().quit();
    }

    /*@Test
    public void logoutTest() {
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.Login("Admin", "admin123");
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.Logout();

        Assert.assertTrue(getDriver().findElement(By.id("btnLogin")).isDisplayed());
        getDriver().quit();
    }*/



}
