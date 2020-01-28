package com.solvd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends AbstractPage{
    @FindBy(id = "searchSystemUser_userName")
    private WebElement searchInput;

    @FindBy(id = "searchBtn")
    private WebElement searchBtn;

    @FindBy(id = "btnAdd")
    private WebElement addBtn;

    public AdminPage(){}

    public void searchUsername(String username) {
        searchInput.sendKeys(username);
        searchBtn.click();
    }

    public void clickAdd() {
        addBtn.click();
    }
}
