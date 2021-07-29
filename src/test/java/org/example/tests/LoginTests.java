package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);

        log4j.startMethod("LoginTests - initTest()");
        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilLoginPageIsLoaded();
        log4j.endMethod("LoginTests - initTest()");
    }

    //negative
    @Test
    public void loginNegativeTest() {
        loginPage.login("email","password");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username",
                "The error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginNegativeTestThirdDataProv(String login, String password) {
        loginPage.login(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this email",
                "The error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeTestMessages")
    public void loginNegativeTestMessages(String login, String password, String message){
        log4j.startTestCase("LoginNegativeTestMessages()\n\t\t\t\t\t\t\t\t\t\t\tparameters: login=" + login + " password=" + password + " message='" + message+"'");
        log4j.info("Enter login notAtlassian: login=" + login + " password=" + password);
        loginPage.login(login,password);
        log4j.info("Assert: Message is - " + message);
        Assert.assertEquals(loginPage.getErrorMessage(),message,
                "The error message is not correct");
        log4j.endTestCase("LoginNegativeTestMessages()");
    }
//String
    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeTestRandom")
    public void loginNegativeTestRandom(String login, String password){
        loginPage.login(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
    }
//Email
    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeTestRandom1")
    public void loginNegativeTestRandom1(String login, String password){
        loginPage.login(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this email",
                "The error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeRandomData")
    public void loginNegativeRandomData(String login, String password){
        loginPage.login(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
    }


    //positive
    @Test(dataProviderClass = DataProviders.class,dataProvider = "loginPositive")
    public void loginPositiveTest(String login, String password) {
//        loginPage.loginAtlassian(LOGIN, PASSWORD);
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "loginPositiveMessage")
    public void loginPositiveTestMessage(String login, String password, String nameButton) {
//        loginPage.loginAtlassian(LOGIN, PASSWORD);
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginPositiveTestSecond(String login, String password) {
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecondMessage")
    public void loginPositiveTestSecondMessage(String login, String password, String nameButton) {
        loginPage.loginAtlassian(login, password);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
    }
}