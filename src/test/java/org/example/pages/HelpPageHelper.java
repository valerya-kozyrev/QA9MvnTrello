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
        log4j.startMethod("HelpPageHelper() - openPage()");
        openHelpPageButton.click();
        log4j.endMethod("HelpPageHelper() - openPage()");
        return this;
    }

    public void waitUntilHelpPageIsLoaded(){
        log4j.startMethod("HelpPageHelper() - waitUntilHelpPageIsLoaded()");
        log4j.info("wait for 'Go To Your Boards' button to be clickable");
        waitUntilElementIsClickable(goToYourBoardsButton, 10);
//        waitUntilElementIsClickable(driver.findElement(By.xpath("//span[contains(text(),'All systems operational')]")),10);
        log4j.endMethod("HelpPageHelper() - waitUntilHelpPageIsLoaded()");
    }

    public void clickOnGoToYourBoardsButton(){
        log4j.startMethod("HelpPageHelper() - clickOnGoToYourBoardsButton()");
        goToYourBoardsButton.click();
        log4j.endMethod("HelpPageHelper() - clickOnGoToYourBoardsButton()");
    }

    public void helpPageIsActive() {
        log4j.startMethod("HelpPageHelper() - helpPageIsActive()");
        String firstWindowHandle = driver.getWindowHandle();
        log4j.info("wait for the window");
        waitUntilWindowsToBe(2,15);
        String secondWindowHandle = getAnotherHandle(firstWindowHandle);
        log4j.info("switch to the other window");
        driver.switchTo().window(secondWindowHandle);
        log4j.info("wait for 'Go To Your Boards' button to be clickable");
        waitUntilElementIsClickable(goToYourBoardsButton,20);
        log4j.endMethod("HelpPageHelper() - helpPageIsActive()");
    }

    public String getHeaderText(){
        log4j.startMethod("HelpPageHelper() - getHeaderText()");
        log4j.endMethod("HelpPageHelper() - getHeaderText()");
        return header.getText();
    }

    public void closeHelpPage() {
        log4j.startMethod("HelpPageHelper() - loseHelpPage()");
        String currentWindow = driver.getWindowHandle();
        String anotherWindow = this.getAnotherHandle(currentWindow);
        log4j.info("close current window");
        this.closeCurrentWindow();
        log4j.info("switch to the other window");
        driver.switchTo().window(anotherWindow);
        log4j.endMethod("HelpPageHelper() - loseHelpPage()");
    }
}
