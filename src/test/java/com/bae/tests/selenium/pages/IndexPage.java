package com.bae.tests.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {
    @FindBy(xpath = "/html/body/div/button")
    private WebElement addUserButton;

    @FindBy(xpath = "/html/body/div[4]/table/tr[1]/td")
    private WebElement usersTable;

    @FindBy(id = "userName")
    private WebElement nameInput;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[3]/button[2]")
    private WebElement submitButton;

    public void addUser(String user) {
        this.addUserButton.click();
        this.nameInput.sendKeys(user);
        this.submitButton.click();
    }

    public boolean checkUser(String user) {
        return usersTable.getText().contains(user);
    }

    public void login() {
        this.usersTable.click();
    }

}
