package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase{

    @FindBy(css=".text-primary")
    WebElement loginIcon;

    public HomePageHelper(WebDriver driver){
        this.driver = driver;
    }

    public boolean isCorrectPage() {
        log4j.startMethod("HomePageHelper() - isCorrectPage");
//        log4j.endMethod("HomePageHelper() - isCorrectPage"); // no use: nothing in between
        return loginIcon.getText().equals("Log in");

    }
    public void waitUntilBeforeLoginPageIsLoaded(){
        log4j.startMethod("HomePageHelper() - waitUntilBeforeLoginPageIsLoaded()");
        waitUntilElementIsClickable(loginIcon, 30);
        log4j.endMethod("HomePageHelper() - waitUntilBeforeLoginPageIsLoaded()");
    }


}
