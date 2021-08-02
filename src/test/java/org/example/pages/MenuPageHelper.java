package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPageHelper extends PageBase {

    @FindBy(css = ".js-open-header-member-menu")
    WebElement openMenuPage;
    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profileAndVisibilityButton;
    @FindBy(xpath = "//*[contains(text(),'Help')]")
    List<WebElement> helpMenuList;
    @FindBy(xpath = "//span[contains(text(),'Activity')]")
    List<WebElement> activityMenuList;
    @FindBy(xpath = "//*[@href='/valerya_kozyrev/activity']")
    WebElement activityButton;


    public MenuPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public MenuPageHelper openMenuPage() {
        log4j.startMethod("MenuPageHelper() - openPage()");
        openMenuPage.click();
        log4j.endMethod("MenuPageHelper() - openPage()");
        return this;
    }

    public void waitUntilPageIsLoaded() {
        log4j.startMethod("MenuPageHelper() - waitUntilMenuPageIsLoaded()");
        log4j.info("wait for 'Profile and Visibility' button to be clickable");
        waitUntilElementIsClickable(profileAndVisibilityButton,5);
        log4j.info("wait for 'Activity' button to be clickable");
        waitUntilElementIsClickable(activityMenuList.get(1),5);
        log4j.info("wait for 'Help' button to be clickable");
        waitUntilElementIsClickable(helpMenuList.get(1),5);
        log4j.endMethod("MenuPageHelper() - waitUntilMenuPageIsLoaded()");
    }

    public void waitUntilMenuPageIsLoaded() {
        log4j.startMethod("MenuPageHelper() - waitUntilMenuPageIsLoaded()");
        log4j.info("wait for 'Profile and Visibility' button to be clickable");
        waitUntilElementIsClickable(profileAndVisibilityButton, 5);
        log4j.endMethod("MenuPageHelper() - waitUntilMenuPageIsLoaded()");
    }

    public String getProfileVisibilityMenuName() {
        log4j.startMethod("MenuPageHelper() - getProfileVisibilityMenuName()");
        log4j.endMethod("MenuPageHelper() - getProfileVisibilityMenuName()");
        return profileAndVisibilityButton.getText();
    }
    public void openActivityPage() {
        log4j.startMethod("MenuPageHelper() - openActivityPage()");
        activityButton.click();
        log4j.endMethod("MenuPageHelper() - openActivityPage()");
    }

    public void openHelpWindow() {
        log4j.startMethod("MenuPageHelper() - openHelpWindow()");
        helpMenuList.get(1).click();
        log4j.endMethod("MenuPageHelper() - openHelpWindow()");
    }


//
//    public void openActivityPage() {
//        log4j.startMethod("MenuPageHelper() - openActivityPage()");
//        activityButton.click();
//        log4j.endMethod("MenuPageHelper() - openActivityPage()");
//    }

}
