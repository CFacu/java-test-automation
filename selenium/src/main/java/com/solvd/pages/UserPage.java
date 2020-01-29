package com.solvd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage extends AbstractPage{
    @FindBy(id = "menu_admin_viewAdminModule")
    private WebElement adminBtn;

    @FindBy(id = "btnAdd")
    private WebElement addBtn;

    @FindBy(id = "systemUser_userType")
    private WebElement roleSelect;

    @FindBy(id = "systemUser_employeeName_empName")
    private WebElement employeeNameInput;

    @FindBy(id = "systemUser_userName")
    private WebElement usernameInput;

    @FindBy(id = "systemUser_status")
    private WebElement statusSelect;

    @FindBy(id = "btnSave")
    private WebElement saveBtn;

    @FindBy(id = "searchBtn")
    protected WebElement searchUsernameBtn;

    @FindBy(id = "searchSystemUser_userName")
    protected WebElement searchUsernameInput;

    public UserPage(){}

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public void addUser(String role, String employeeName, String username, String status) {
        adminBtn.click();
        addBtn.click();
        Select selectRole = new Select(roleSelect);
        selectRole.selectByVisibleText(role);
        employeeNameInput.sendKeys(employeeName);
        usernameInput.sendKeys(username);
        Select selectStatus = new Select(statusSelect);
        selectStatus.selectByValue(status);
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();

    }

    public boolean userExists(String username) {
        searchUsernameInput.sendKeys(username);
        searchUsernameBtn.click();
        if (driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]")).equals(username))
            return true;
        else
            return false;
    }
}
