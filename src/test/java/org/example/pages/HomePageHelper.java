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

    public void waitUntilHomePageIsLoaded(){
        log4j.startMethod("HomePageHelper() - waitUntilHomePageIsLoaded()");
        waitUntilElementIsClickable(loginIcon, 5);
        log4j.endMethod("HomePageHelper() - waitUntilHomePageIsLoaded()");
    }

    public boolean isCorrectPage() {
        log4j.startMethod("HomePageHelper() - isCorrectPage");
        log4j.endMethod("HomePageHelper() - isCorrectPage"); // no use: nothing in between
        return loginIcon.getText().equals("Log in");
    }


//    public void openLoginPage(){
//        log4j.startMethod("HomePageHelper() - openLoginPage()");
//        loginIcon.click();
//        log4j.endMethod("HomePageHelper() - openLoginPage()");
//    }


}
