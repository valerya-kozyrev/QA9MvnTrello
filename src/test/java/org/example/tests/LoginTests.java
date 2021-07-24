package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.LoginPageHelper;
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
        loginPage.openPage();
        loginPage.waitUntilLoginPageIsLoaded();
    }

    @Test
    public void loginNegativeTest() {
        loginPage.login("email","password");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username",
                "The error message is not correct");
    }

    @Test
    public void loginPositiveTest() {

        loginPage.loginAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");
    }
}