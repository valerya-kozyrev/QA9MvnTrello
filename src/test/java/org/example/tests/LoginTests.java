package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);

        log4j.startMethod("LoginTests - initTest()");
        homePage.waitUntilHomePageIsLoaded();
        loginPage
                .openPage()
                .waitUntilLoginPageIsLoaded();
        log4j.endMethod("LoginTests - initTest()");
    }


    //negative
    @Test
    public void loginNegativeTest() {
        log4j.startTestCase("loginNegativeTest()");
        loginPage.login("email","password");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username",
                "The error message is not correct");
        log4j.endTestCase("loginNegativeTest()");
    }

    @Test(groups={"smoke","system"}, dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginNegativeTestThirdDataProv(String login, String password) {
        log4j.startTestCase("loginNegativeTestThirdDataProv()");
        loginPage.login(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this email",
                "The error message is not correct");
        log4j.endTestCase("loginNegativeTestThirdDataProv()");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeTestMessages")
    public void loginNegativeTestMessages(String login, String password, String message){
        log4j.startTestCase("loginNegativeTestMessages()\n\t\t\t\t\t\t\t\t\t\t\tparameters: login=" + login + " password=" + password + " message='" + message+"'");
        log4j.info("Enter login notAtlassian: login=" + login + " password=" + password);
        loginPage.login(login,password);
        log4j.info("Assert: Message is - " + message);
        Assert.assertEquals(loginPage.getErrorMessage(),message,
                "The error message is not correct");
        log4j.endTestCase("loginNegativeTestMessages()");
    }
//String
    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeTestRandom")
    public void loginNegativeTestRandom(String login, String password){
        log4j.startTestCase("loginNegativeTestRandom()");
        loginPage.login(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
        log4j.endTestCase("loginNegativeTestRandom()");
    }
//Email
    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeTestRandom1")
    public void loginNegativeTestRandom1(String login, String password){
        log4j.startTestCase("loginNegativeTestRandom1()");
        loginPage.login(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this email",
                "The error message is not correct");
        log4j.endTestCase("loginNegativeTestRandom1()");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeRandomData")
    public void loginNegativeRandomData(String login, String password){
        log4j.startTestCase("loginNegativeRandomData()");
        loginPage.login(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
        log4j.endTestCase("loginNegativeRandomData()");
    }


    //positive
    @Test
    public void loginPositiveTest() {
        log4j.startTestCase("loginPositiveTest()");
        loginPage.loginAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
        log4j.endTestCase("loginPositiveTest()");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "loginPositive")
    public void loginPositiveTest(String login, String password) {
        log4j.startTestCase("loginPositiveTest()");
//        loginPage.loginAtlassian(LOGIN, PASSWORD);
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
        log4j.endTestCase("loginPositiveTest()");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "loginPositiveMessage")
    public void loginPositiveTestMessage(String login, String password, String nameButton) {
        log4j.startTestCase("loginPositiveTestMessage()");
//        loginPage.loginAtlassian(LOGIN, PASSWORD);
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
        log4j.endTestCase("loginPositiveTestMessage()");
    }

    @Test(groups={"smoke","system"}, dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginPositiveTestSecond(String login, String password) {
        log4j.startTestCase("loginPositiveTestSecond()");
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
        log4j.endTestCase("loginPositiveTestSecond()");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecondMessage")
    public void loginPositiveTestSecondMessage(String login, String password, String nameButton) {
        log4j.startTestCase("loginPositiveTestSecondMessage()");
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
        log4j.endTestCase("loginPositiveTestSecondMessage()");
    }
}