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
        openMenuPage.click();
        return this;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(profileAndVisibilityButton,5);
        waitUntilElementIsClickable(activityMenuList.get(1),5);
        waitUntilElementIsClickable(helpMenuList.get(1),5);
    }

    public void waitUntilMenuPageIsLoaded() {
        waitUntilElementIsClickable(profileAndVisibilityButton, 5);
    }

    public String getProfileVisibilityMenuName() {
        return profileAndVisibilityButton.getText();
    }

    public void clickActivityButton() {
        activityButton.click();
    }

    public void openHelpWindow() {
        helpMenuList.get(1).click();
    }
}
