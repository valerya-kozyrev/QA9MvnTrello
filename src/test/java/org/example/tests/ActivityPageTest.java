package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.*;

public class ActivityPageTest extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        activityPage = PageFactory.initElements(driver,ActivityPageHelper.class);

        log4j.startMethod("ActivityPageTest - initTest()");
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
        activityPage
                .openPage()
                .waitUntilActivityPageIsLoaded();
        log4j.endMethod("ActivityPageTest - initTest()");
    }

    @Test
    public void verifyAddNewListTest() {
        log4j.startTestCase("verifyAddNewListTest");
        log4j.info("initial quantity of records in activity list");
        int sizeActivityBefore = activityPage.getActivityListSize();
        log4j.info("return to menu page");
        activityPage.returnToPreviousPage();
        menuPage.waitUntilMenuPageIsLoaded();
//        qa9Board.waitUntilCurrentBoardIsLoaded();
        log4j.info("create new list");
        qa9Board.createNewList("Activity List");
        menuPage.openMenuPage();
        menuPage.openActivityPage();
        activityPage.waitUntilActivityPageIsLoaded();
        activityPage.findLastActivityText();
        int sizeActivityListAfter = activityPage.getActivityListSize();
        Assert.assertEquals(sizeActivityListAfter, sizeActivityBefore + 1);
        Assert.assertTrue(activityPage.lastActivityContains("Activity List"));
//        Assert.assertEquals(activityPage.findLastActivityText(),
//                driver.findElement(By.xpath("//div[@class='phenom mod-attachment-type'][1]")).getText(),
//                "Wrong activity");
        log4j.endTestCase("verifyAddNewListTest");
    }

}
