package org.example.tests;

import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.BoardsPageHelper;
import org.example.pages.CurrentBoardHelper;
import org.example.pages.LoginPageHelper;

public class CurrentBoardTest extends TestBase {

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");

        log4j.startMethod("qa9Board - initTest()");
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
        log4j.endMethod("qa9Board - initTest()");
    }

    @Test
    public void addNewListTest() {
        log4j.startTestCase("addNewListTest");
        log4j.info("initial quantity of lists in QA9 board");
        int numberOfListsBefore = qa9Board.getListSize();
        log4j.info("create new list");
        qa9Board.createNewList("New List");
        log4j.info("final quantity of lists in QA9 board");
        int numberOfListsAfter = qa9Board.getListSize();
        log4j.info("Assert: one more list added");
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
        log4j.endTestCase("addNewListTest");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "createNewList")
    public void addNewListTestParam(String nameList) {
        int numberOfListsBefore = qa9Board.getListSize();
        qa9Board.createNewList(nameList);
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
    }

    @Test
    public void addNewCardTest() {
        qa9Board.getNumberOfListsBefore("New Card List");
        int numberOfCardsBefore = qa9Board.getCardSize();
        qa9Board.createNewCard();
        int numberOfCardsAfter = qa9Board.getCardSize();
        Assert.assertEquals(numberOfCardsAfter, numberOfCardsBefore + 1);
    }

    @Test
    public void copyListTest() {
        int numberOfListsBefore = qa9Board.getNumberOfListsBefore("Copy List");
        qa9Board.copyList("Changed");
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
    }

    @Test
    public void archiveListTest() {
        int numberOfListsBefore = qa9Board.getNumberOfListsBefore("Archieve List");
        qa9Board.archiveList();
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
    }

    @Test
    public void archiveNameListTest() {
        String nameList = "Archieve Name List";
        int numberOfListsBefore = qa9Board.getListSize();
        int number = qa9Board.getNumberOfElementWithName(nameList);
        if (number == -1) {
            qa9Board.createNewList("Archieve Name List");
            number = numberOfListsBefore;
            numberOfListsBefore++;
        }
        qa9Board.archiveNameList(number);
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
    }


    @Test
    public void archiveNameListTest1() {
        int numberOfListsBefore = qa9Board.getListSize();
        if (qa9Board.getNameListSize() == 0) {

            qa9Board.createNewList("Other List");

            numberOfListsBefore++;
        }
//        qa9Board.getNumberOfListsBefore1("Other List");
        qa9Board.archiveNameList1("Other List");
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
    }
}
/*
    @Test
    public void archiveNameListTest1() {

        int numberOfListsBefore = qa9Board.getListSize();
        List<WebElement> listWithName = qa9Board.getNameElements(By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'Other List')]]"));

        if (listWithName.size() == 0) {

            qa9Board.createNewList("Other List");

            numberOfListsBefore++;
        }
        qa9Board.waitUntilAllElementsArePresent(By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'Other List')]]"), 10);

        // click on the list menu
        qa9Board.waitUntilElementIsClickable(By.className("list-header-extras-menu"), 10);
        driver.findElement(By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'Other List')]]//*[@class='list-header-extras']")).click();

        // click on "Archive this list"
        qa9Board.clickOnArchiveList();

        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore - 1);
    }*/



