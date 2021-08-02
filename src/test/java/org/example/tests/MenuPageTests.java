package org.example.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.*;

public class MenuPageTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);

        log4j.startMethod("MenuPageTest - initTest()");
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
                .waitUntilMenuPageIsLoaded();
        log4j.endMethod("MenuPageTest - initTest()");
    }

    @Test
    public void profileVisibilityMenuExistsTest() {
        log4j.startTestCase("profileVisibilityMenuExistsTest");
        Assert.assertEquals(menuPage.getProfileVisibilityMenuName(), "Profile and visibility");
        log4j.endTestCase("profileVisibilityMenuExistsTest");
    }
}
