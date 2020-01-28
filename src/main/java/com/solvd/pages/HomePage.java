package com.solvd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    /*@FindBy(linkText = "Logout")
    private WebElement logoutBtn;*/

    public HomePage(){

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

    /*public void logout(){
        welcomeTrigger.click();
        logoutBtn.click();
    }*/
}
