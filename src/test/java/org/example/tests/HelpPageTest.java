package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpPageTest extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;
    MenuPageHelper menuPage;
    HelpPageHelper helpPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        helpPage = PageFactory.initElements(driver, HelpPageHelper.class);

        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilLoginPageIsLoaded()
                .loginAtlassian(LOGIN, PASSWORD);
        boardsPage
                .waitUntilBoardPageIsLoaded()
                .openBoardsMenu();
        qa9Board
                .openPage()
                .waitUntilCurrentBoardIsLoaded();
        menuPage
                .openMenuPage()
                .waitUntilPageIsLoaded();
        menuPage.openHelpWindow();
        helpPage.helpPageIsActive();
    }

        @Test
    public void helpWindowVerification(){
        Assert.assertEquals(helpPage.getHeaderText(), "Get help with Trello");
    }

    @Test
    public void openHelpPageInNewWindow() {
        helpPage.closeHelpPage();
        qa9Board.waitUntilCurrentBoardIsLoaded();
        Assert.assertTrue(qa9Board.isCorrectPage());
    }

    @Test
    public void openHelpPageAndGoToYourBoards() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Go to your boards')]")).isDisplayed());
        helpPage.clickOnGoToYourBoardsButton();
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA9']]")).isDisplayed());
    }
}
