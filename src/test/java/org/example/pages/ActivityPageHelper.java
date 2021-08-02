package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase {

    @FindBy(xpath = "//*[@href='/valerya_kozyrev/activity']")
    WebElement activityButton;
    @FindBy(css = ".phenom-desc")
    List<WebElement> activityRecordsList;
    @FindBy(xpath = "//div[@class='phenom mod-attachment-type']")
    List<WebElement> activityList;
    @FindBy(xpath = "//div[@class='phenom mod-attachment-type'][1]")
WebElement lastActivityElement;

    public ActivityPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public ActivityPageHelper openPage() {
        log4j.startMethod("ActivityPageHelper() - openPage()");
        waitUntilElementIsClickable(activityButton,10);
        activityButton.click();
        log4j.endMethod("ActivityPageHelper() - openPage()");
        return this;
    }

    public void waitUntilActivityPageIsLoaded(){
        log4j.startMethod("ActivityPageHelper() - waitUntilActivityPageIsLoaded()");
        waitUntilAllElementsAreVisible(activityList,10);
        log4j.endMethod("ActivityPageHelper() - waitUntilActivityPageIsLoaded()");
    }

    public int getActivityListSize() {
        log4j.startMethod("ActivityPageHelper() - getActivityListSize()");
        log4j.endMethod("ActivityPageHelper() - getActivityListSize()");
        return activityList.size();
    }

    public void returnToPreviousPage() {
        log4j.startMethod("ActivityPageHelper() - returnToPreviousPage()");
        driver.navigate().back();
        log4j.endMethod("ActivityPageHelper() - returnToPreviousPage()");
    }

    public String findLastActivityText() {
//        WebElement lastActivity = driver.findElement(By.xpath("///div[@class='phenom mod-attachment-type'][.//*[contains(text(),' added list Verify List to ')]]"));
        log4j.startMethod("ActivityPageHelper() - findLastActivityText()");
        log4j.endMethod("ActivityPageHelper() - findLastActivityText()");
        return lastActivityElement.getText();
    }

    public boolean lastActivityContains(String text){
        log4j.startMethod("ActivityPageHelper() - lastActivityContains()");
        log4j.endMethod("ActivityPageHelper() - lastActivityContains()");
        return activityRecordsList.get(0).getText().contains(text);
    }
}
