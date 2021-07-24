package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpPageHelper extends PageBase{

    @FindBy(xpath = "//span[contains(text(),'Go to your boards')]")
    WebElement goToYourBoardsButton;
    @FindBy(xpath = "//span[contains(text(),'Help')]")
    WebElement openHelpPageButton;


    public HelpPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public HelpPageHelper openHelpPage() {
        openHelpPageButton.click();
        return this;
    }

    public void waitUntilPageIsLoaded(){
//        waitUntilElementIsClickable(goToYourBoardsButton, 10);
        waitUntilElementIsClickable(driver.findElement(By.xpath("//span[contains(text(),'All systems operational')]")),10);
    }

    public void clickOnGoToYourBoardsButton(){
        goToYourBoardsButton.click();
    }

    public void helpPageIsActive() throws InterruptedException {
        String firstWindowHandle = driver.getWindowHandle();
        openHelpPage();
        Thread.sleep(3000);
        String secondWindowHandle = "";
        for(String handle: driver.getWindowHandles()){
            if(!handle.equals(firstWindowHandle)) secondWindowHandle=handle;
        }
        driver.switchTo().window(secondWindowHandle);
//        helpPage.waitUntilPageIsLoaded();
    }

}
