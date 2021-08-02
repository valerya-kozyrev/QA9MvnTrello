package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoardsPageHelper extends PageBase {

    @FindBy(xpath = "//span[contains(text(),'Boards')]") // "//button[@aria-label='Open boards menu']") // "//button[@data-test-id='header-boards-menu-button']/span[2]")
    WebElement boardsIcon; // "//span[@data-test-id=\"home-team-tab-name\"]") - workspace
    @FindBy(xpath = "//a[@data-test-id = 'home-team-boards-tab']")
    WebElement boardsMenuTab;
    @FindBy(xpath = "//a[@class = 'board-tile'][.//div[@title='QA9']]")
    WebElement qa9Board;
    @FindBy(xpath = "//h3")
    WebElement e;

    public BoardsPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilBoardPageIsLoaded(){
        log4j.startMethod("BoardsPageHelper() - waitUntilCurrentBoardIsLoaded()");
        log4j.info("wait until 'Board' button is clickable and click it");
        waitUntilElementIsClickable(boardsIcon, 30);
        log4j.endMethod("BoardsPageHelper() - waitUntilCurrentBoardIsLoaded()");
        return this;
    }

    public String getBoardsButtonName(){
        log4j.startMethod("BoardsPageHelper() - waitUntilCurrentBoardIsLoaded()");
        log4j.endMethod("BoardsPageHelper() - getBoardsButtonName()");
        return boardsIcon.getText();
    }

    public void openBoardsMenu(){
        log4j.startMethod("BoardsPageHelper() - getBoardsButtonName()");
        log4j.info("wait until 'Board' menu button is clickable and click it");
        waitUntilElementIsClickable(boardsMenuTab,10);
        boardsMenuTab.click();
        log4j.info("wait until text 'Board' is visible");
        waitUntilElementTextIs(e,"Your Workspace boards",10);
        log4j.endMethod("BoardsPageHelper() - waitUntilCurrentBoardIsLoaded()");
    }


//    public void openCurrentBoard(){
//        waitUntilElementIsClickable(qa9Board,10);
//        qa9Board.click();
//        waitUntilElementIsClickable(getLocatorBoardButton(),10);
//        WebElement qa9Board = driver.findElement(getLocatorBoardButton());
//        qa9Board.click();
//    }

//    public By getLocatorBoardButton() {
//        log4j.startMethod("CurrentPageHelper() - getLocatorBoardButton()");
//        log4j.endMethod("CurrentPageHelper() - getLocatorBoardButton()");
//        return By.xpath("//a[@class = 'board-tile'][.//div[@title='" + boardName + "']]");
//    }
}
