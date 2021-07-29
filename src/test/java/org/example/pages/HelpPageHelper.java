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
    @FindBy (xpath = "//h1")
    WebElement header;


    public HelpPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public HelpPageHelper openHelpPage() {
        openHelpPageButton.click();
        return this;
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(goToYourBoardsButton, 10);
//        waitUntilElementIsClickable(driver.findElement(By.xpath("//span[contains(text(),'All systems operational')]")),10);
    }

    public void clickOnGoToYourBoardsButton(){
        goToYourBoardsButton.click();
    }

    public void helpPageIsActive() {
        String firstWindowHandle = driver.getWindowHandle();
        waitUntilWindowsToBe(2,15);
        String secondWindowHandle = getAnotherHandle(firstWindowHandle);
        driver.switchTo().window(secondWindowHandle);
        waitUntilElementIsClickable(goToYourBoardsButton,20);
    }

    public String getHeaderText(){
        return header.getText();
    }


    public void closeHelpPage() {
        String currentWindow = driver.getWindowHandle();
        String anotherWindow = this.getAnotherHandle(currentWindow);
        this.closeCurrentWindow();
        driver.switchTo().window(anotherWindow);
    }
}
