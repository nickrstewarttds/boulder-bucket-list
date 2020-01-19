package com.bae.tests.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BucketListPage {

    @FindBy(xpath = "/html/body/div[5]/button")
    private WebElement addBoulderButton;

    @FindBy(id = "boulderName")
    private WebElement nameField;

    @FindBy(id = "boulderLocation")
    private WebElement locationField;

    @FindBy(id = "boulderGrade")
    private WebElement gradeField;

    @FindBy(id = "boulderStatus")
    private WebElement statusField;

    @FindBy(id = "boulderAttemptDate")
    private WebElement attemptDateField;

    @FindBy(id = "boulderCompletionDate")
    private WebElement completionDateField;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "update")
    private WebElement updateButton;

    @FindBy(id = "delete")
    private WebElement deleteButton;

    @FindBy(xpath = "/html/body/div[8]/div/div/div[3]/button[2]")
    private WebElement confirmDeleteButton;

    @FindBy(id = "name1")
    private WebElement nameEntry;

    @FindBy(id = "location1")
    private WebElement locationEntry;

    @FindBy(id = "grade1")
    private WebElement gradeEntry;

    @FindBy(id = "status1")
    private WebElement statusEntry;

    @FindBy(id = "attemptDate1")
    private WebElement attemptDateEntry;

    @FindBy(id = "completionDate1")
    private WebElement completionDateEntry;

    public void addBoulder(String name, String location, String grade, String status, String attemptDate, String completionDate) {
        this.addBoulderButton.click();
        this.nameField.sendKeys(name);
        this.locationField.sendKeys(location);
        this.gradeField.sendKeys(grade);
        this.statusField.sendKeys(status);
        this.attemptDateField.sendKeys(attemptDate);
        this.completionDateField.sendKeys(completionDate);
        this.submitButton.click();
    }

    public void updateBoulder(String name, String location, String grade, String status, String attemptDate, String completionDate) {
        this.updateButton.click();
        this.nameField.clear();
        this.locationField.clear();
        this.attemptDateField.clear();
        this.completionDateField.clear();
        this.nameField.sendKeys(name);
        this.locationField.sendKeys(location);
        this.gradeField.sendKeys(grade);
        this.statusField.sendKeys(status);
        this.attemptDateField.sendKeys(attemptDate);
        this.completionDateField.sendKeys(completionDate);
        this.submitButton.click();
    }

    public void deleteBoulder() {
        this.deleteButton.click();
        this.confirmDeleteButton.click();
    }

    public boolean checkBoulder(String name, String location, String grade, String status, String attemptDate, String completionDate) {
        return this.nameEntry.getText().equals(name) &&
                this.locationEntry.getText().equals(location) &&
                this.gradeEntry.getText().equals(grade) &&
                this.statusEntry.getText().equals(status) &&
                this.attemptDateEntry.getText().equals(attemptDate) &&
                this.completionDateEntry.getText().equals(completionDate);
    }

}
