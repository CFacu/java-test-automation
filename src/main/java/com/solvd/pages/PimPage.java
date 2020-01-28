package com.solvd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PimPage extends AbstractPage {
    @FindBy(id = "menu_pim_addEmployee")
    private WebElement addEmplyeeHyperLink;

    public PimPage() {

    }

    public void clickAddEmployee() {
        addEmplyeeHyperLink.click();
    }
}
