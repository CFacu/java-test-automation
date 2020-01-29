package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage{
    @FindBy(id = "menu_admin_viewAdminModule")
    private WebElement adminHyperlink;

    @FindBy(id = "menu_leave_viewLeaveModule")
    private WebElement leaveHyperLink;

    @FindBy(id = "menu_time_viewTimeModule")
    private WebElement timeHyperLink;

    @FindBy(id = "welcome")
    private WebElement welcomeTrigger;

    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement pimHyperLink;

    @FindBy(xpath = "//*[@id='welcome-menu']/ul/li[2]/a")
    private WebElement logoutHyperLink;

    public HomePage(){}

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAdmin() {
        adminHyperlink.click();
    }

    public void clickLeave() {
        leaveHyperLink.click();
    }

    public void clickTime() {
        timeHyperLink.click();
    }

    public void clickWelcome() {
        welcomeTrigger.click();
    }

    public void clickPim() {
        pimHyperLink.click();
    }

    public WebElement getAdminHyperlink() {
        return adminHyperlink;
    }

    public WebElement getPimHyperLink() {
        return pimHyperLink;
    }

    public void logout(){
        welcomeTrigger.click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(logoutHyperLink));
        logoutHyperLink.click();
    }
}
