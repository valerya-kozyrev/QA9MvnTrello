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
    ActivityPageHelper activityPage;
    HelpPageHelper helpPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);
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
//        helpPage
//                .openHelpPage();
//                .waitUntilPageIsLoaded();
    }

    @Test
    public void openHelpPageInNewWindow() throws InterruptedException {
        String firstWindowHandle = driver.getWindowHandle();
        helpPage.openHelpPage();
        Thread.sleep(3000);
        String secondWindowHandle = "";
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(firstWindowHandle)) secondWindowHandle = handle;
        }
        driver.switchTo().window(secondWindowHandle);
//        helpPage.waitUntilPageIsLoaded();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'All systems operational')]")).isDisplayed());
        driver.close();
        driver.switchTo().window(firstWindowHandle);
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Help')]")).isDisplayed());
    }

    @Test
    public void openHelpPageAndGoToYourBoards() throws InterruptedException {
        helpPage.helpPageIsActive();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Go to your boards')]")).isDisplayed());
        helpPage.clickOnGoToYourBoardsButton();
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA9']]")).isDisplayed());
    }
}
