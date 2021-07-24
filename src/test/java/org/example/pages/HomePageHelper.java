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
        return loginIcon.getText().equals("Log in");

    }
    public void waitUntilBeforeLoginPageIsLoaded(){
        waitUntilElementIsClickable(loginIcon, 30);
    }


}
