package org.example.tests;

import org.example.pages.HomePageHelper;
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
        log4j.startTestCase("addNewListTestParam");
        log4j.info("initial quantity of lists in QA9 board");
        int numberOfListsBefore = qa9Board.getListSize();
        log4j.info("create new list");
        qa9Board.createNewList(nameList);
        log4j.info("final quantity of lists in QA9 board");
        int numberOfListsAfter = qa9Board.getListSize();
        log4j.info("Assert: one more list added");
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
        log4j.endTestCase("addNewListTestParam");
    }

    @Test
    public void addNewCardTest() {
        log4j.startTestCase("addNewCardTest");
        log4j.info("initial quantity of lists in QA9 board");
        qa9Board.getNumberOfListsBefore("New Card List");
        log4j.info("initial quantity of cards in QA9 board");
        int numberOfCardsBefore = qa9Board.getCardSize();
        log4j.info("create new card");
        qa9Board.createNewCard();
        log4j.info("final quantity of cards in QA9 board");
        int numberOfCardsAfter = qa9Board.getCardSize();
        log4j.info("Assert: one more card added");
        Assert.assertEquals(numberOfCardsAfter, numberOfCardsBefore + 1);
        log4j.endTestCase("addNewCardTest");
    }

    @Test
    public void copyListTest() {
        log4j.startTestCase("copyListTest");
        log4j.info("initial quantity of lists in QA9 board");
        int numberOfListsBefore = qa9Board.getNumberOfListsBefore("Copy List");
        log4j.info("copy list");
        qa9Board.copyList("Changed");
        log4j.info("final quantity of lists in QA9 board");
        int numberOfListsAfter = qa9Board.getListSize();
        log4j.info("Assert: one more list added");
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
        log4j.endTestCase("copyListTest");
    }

    @Test
    public void archiveListTest() {
        log4j.startTestCase("archiveListTest");
        log4j.info("initial quantity of lists in QA9 board");
        int numberOfListsBefore = qa9Board.getNumberOfListsBefore("Archive List");
        log4j.info("archive list");
        qa9Board.archiveList();
        log4j.info("final quantity of lists in QA9 board");
        int numberOfListsAfter = qa9Board.getListSize();
        log4j.info("Assert: one list was reduced");
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
        log4j.endTestCase("archiveListTest");
    }

    @Test
    public void archiveNameListTest() {
        log4j.startTestCase("archiveNameListTest");
        String nameList = "Archive Name List";
        log4j.info("initial quantity of lists in QA9 board");
        int numberOfListsBefore = qa9Board.getListSize();
        int number = qa9Board.getNumberOfElementWithName(nameList);
        if (number == -1) {
            log4j.info("create new list");
            qa9Board.createNewList("Archive Name List");
            number = numberOfListsBefore;
            numberOfListsBefore++;
        }
        log4j.info("archive list");
        qa9Board.archiveNameList(number);
        log4j.info("final quantity of lists in QA9 board");
        int numberOfListsAfter = qa9Board.getListSize();
        log4j.info("Assert: one list was reduced");
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
        log4j.endTestCase("archiveNameListTest");
    }


    @Test
    public void archiveNameListTest1() {
        log4j.startTestCase("archiveNameListTest1");
        log4j.info("initial quantity of lists in QA9 board");
        int numberOfListsBefore = qa9Board.getListSize();
        if (qa9Board.getNameListSize() == 0) {
            log4j.info("create new list");
            qa9Board.createNewList("Other List");
            numberOfListsBefore++;
        }
        log4j.info("archive list");
//        qa9Board.getNumberOfListsBefore1("Other List");
        qa9Board.archiveNameList1("Other List");
        log4j.info("final quantity of lists in QA9 board");
        int numberOfListsAfter = qa9Board.getListSize();
        log4j.info("Assert: one list was reduced");
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
        log4j.endTestCase("archiveNameListTest1");
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



