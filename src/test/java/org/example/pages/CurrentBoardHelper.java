package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CurrentBoardHelper extends PageBase {

    @FindBy(xpath = "//a[@class = 'board-tile'][.//div[@title='QA9']]")
    WebElement qa9Board;
    @FindBy(css=".placeholder")
    WebElement addAnotherListElement;
    @FindBy(css=".js-list-content")
    List<WebElement> listOfLists;
    @FindBy(css=".list-card-title")
    List<WebElement> listOfCards;
    @FindBy(className = "list-name-input")
    WebElement titleListField;
    @FindBy(css = ".js-save-edit")
    WebElement addListButton;
    @FindBy(css=".js-cancel-edit")
    WebElement cancelAddAnotherList;
    @FindBy(css=".card-composer-container")
    WebElement addACardButton;
    @FindBy(css=".js-card-title")
    WebElement cardTitleField;
    @FindBy(css=".js-add-card")
    WebElement addCardButton;
    @FindBy(css=".js-cancel")
    WebElement cancelAddAnotherCard;
    @FindBy(css=".list-header-extras-menu")
    WebElement listMenuButton;
    @FindBy(css=".js-copy-list")
    WebElement copyListButton;
    @FindBy(css=".js-autofocus")
    WebElement copiedListTitleField;
    @FindBy(css=".js-submit")
    WebElement createCopiedListButton;
    @FindBy(className="js-close-list")
    WebElement archieveThisListButton;
    @FindBy(css=".list-header-name")
    List<WebElement> listName;
    @FindBy (css = ".js-board-editing-target")
    WebElement boardNameButton;

    String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        this.driver = driver;
        this.boardName = boardName;
        PageFactory.initElements(driver,this);
    }

    public CurrentBoardHelper openPage() {
        log4j.startMethod("CurrentPageHelper() - openPage()");
        log4j.info("wait until getLocatorBoardButton() is clickable & click on it");
        waitUntilElementIsClickable(getLocatorBoardButton(),10);
        WebElement qa9Board = driver.findElement(getLocatorBoardButton());
        qa9Board.click();
        log4j.endMethod("CurrentPageHelper() - openPage()");
        return this;
    }

    public By getLocatorBoardButton() {
        log4j.startMethod("CurrentPageHelper() - getLocatorBoardButton()");
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='" + boardName + "']]");
    }

    public void waitUntilCurrentBoardIsLoaded() {
        log4j.startMethod("CurrentBoardHelper() - waitUntilCurrentBoardIsLoaded()");
        log4j.info("wait until 'AnotherList' element is clickable");
        waitUntilElementIsClickable(addAnotherListElement, 10);
        log4j.info("if there's elemnt 'Add another list'");
        if (addAnotherListElement.getText().equals("Add another list")) {
            log4j.startMethod("CurrentBoardHelper() - waitUntilAllElementsAreVisible()");
            log4j.info("wait until all the present lists are visible");
            waitUntilAllElementsAreVisible(listOfLists, 5);
            log4j.startMethod("CurrentBoardHelper() - waitUntilAllElementsAreVisible()");
        }
        log4j.endMethod("CurrentBoardHelper() - waitUntilCurrentBoardIsLoaded()");
    }

    public int getListSize(){
        log4j.startMethod("CurrentPageHelper() - getListSize()");
        return listOfLists.size();
    }

    public int getCardSize(){
        log4j.startMethod("CurrentPageHelper() - getCardSize()");
        return listOfCards.size();
    }

    public int getNumberOfListsBefore(String s) {
        log4j.startMethod("CurrentPageHelper() - getNumberOfListsBefore()");
        log4j.info("intialize variable that shows quantity of lists in QA9 board");
        int numberOfListsBefore = getListSize();
        if (numberOfListsBefore == 0) {
            log4j.startMethod("CurrentPageHelper() - createNewList()");
            createNewList(s);
            log4j.endMethod("CurrentPageHelper() - createNewList()");
            log4j.info("add 1 more list to the quantity of lists in QA9 board");
            numberOfListsBefore++;
        }
        log4j.endMethod("CurrentPageHelper() - getNumberOfListsBefore()");
        return numberOfListsBefore;
    }

//  create new list
    public void createNewList(String s) {
        log4j.startMethod("CurrentPageHelper() - createNewList()");
        log4j.info("intialize variable that shows quantity of lists in QA9 board");
        int beginListsQuantity = this.getListSize();
//        log4j.info("click on button to add another list");
        openAddList();
//        log4j.info("enter list's name");
        enterListName(s);
//        log4j.info("click on 'Add' button to add list");
        clickAddListButton();
        log4j.info("wait until the lists changes to +1");
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
//        log4j.info("cancel adding another list");
        closeAnotherAddList();
        log4j.endMethod("CurrentPageHelper() - createNewList()");
    }

    public void openAddList() {
        log4j.startMethod("CurrentPageHelper() - openAddList()");
        log4j.info("click on 'Add' list button");
        addAnotherListElement.click();
        log4j.endMethod("CurrentPageHelper() - openAddList()");
    }

    public void enterListName(String name) {
        log4j.startMethod("CurrentPageHelper() - enterListName()");
        log4j.info("enter title of the list");
        editField(titleListField, name);
        log4j.endMethod("CurrentPageHelper() - enterListName()");
    }

    public void clickAddListButton() {
        log4j.startMethod("CurrentPageHelper() - clickAddListButton()");
        log4j.info("click on 'Add' list button");
        addListButton.click();
        log4j.endMethod("CurrentPageHelper() - clickAddListButton()");
    }

    public void closeAnotherAddList() {
        log4j.startMethod("CurrentPageHelper() - closeAnotherAddList()");
        log4j.info("wait until button to add another list is clickable and click it");
        waitUntilElementIsClickable(cancelAddAnotherList,5);
        cancelAddAnotherList.click();
        log4j.endMethod("CurrentPageHelper() - closeAnotherAddList()");
    }

//  create new card
    public void createNewCard() {
        log4j.startMethod("CurrentPageHelper() - createNewCard()");
        log4j.info("intialize variable that shows quantity of cards in lists in QA9 board");
        int beginCards = this.getCardSize();
        addNewCard();
        enterCardName("card tile");
        clickAddCardButton();
        log4j.info("Cwait until the cards changes to +1");
        waitUntilElementsBecome(By.cssSelector(".list-card-title"),beginCards+1,10);
        cancelAnotherCard();
        log4j.endMethod("CurrentPageHelper() - createNewCard()");
    }

    public void addNewCard() {
        log4j.startMethod("CurrentPageHelper() - addNewCard()");
        log4j.info("click on the button to add new card");
        addACardButton.click();
        log4j.endMethod("CurrentPageHelper() - addNewCard()");
    }

    public void enterCardName(String s) {
        log4j.startMethod("CurrentPageHelper() - enterCardName()");
        log4j.info("enter title of the card");
        editField(cardTitleField, s);
        log4j.endMethod("CurrentPageHelper() - enterCardName()");
    }

    public void clickAddCardButton() {
        log4j.startMethod("CurrentPageHelper() - clickAddCardButton()");
        log4j.info("click on the button to add new card");
        addCardButton.click();
        log4j.endMethod("CurrentPageHelper() - clickAddCardButton()");
    }

    public void cancelAnotherCard() {
        log4j.startMethod("CurrentPageHelper() - cancelAnotherCard()");
        log4j.info("wait until button to cancel adding another card is clickable and click it");
        waitUntilElementIsClickable(cancelAddAnotherCard,5);
        cancelAddAnotherCard.click();
        log4j.endMethod("CurrentPageHelper() - cancelAnotherCard()");
    }

//  copy
    public void copyList(String s) {
        log4j.startMethod("CurrentPageHelper() - createNewList()");
        log4j.info("intialize variable that shows quantity of lists in QA9 board");
        int beginListsQuantity = this.getListSize();
        clickOnExtraMenuButton();
        clickOnCopyList();
        enterCopiedListName("Changed");
        clickOnCreateList();
        log4j.info("Cwait until the lists changes to +1");
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
        log4j.endMethod("CurrentPageHelper() - cancelAnotherCard()");
    }

    public void clickOnExtraMenuButton() {
        log4j.startMethod("CurrentPageHelper() - clickOnExtraMenuButton()");
        log4j.info("wait until 'menu button' of the list is clickable and click it");
        waitUntilElementIsClickable(listMenuButton,5);
        listMenuButton.click();
        log4j.endMethod("CurrentPageHelper() - clickOnExtraMenuButton()");
    }

    public void clickOnCopyList() {
        log4j.startMethod("CurrentPageHelper() - clickOnCopyList()");
        log4j.info("wait until 'copy list button' is clickable and click it");
        waitUntilElementIsClickable(copyListButton, 10);
        copyListButton.click();
        log4j.endMethod("CurrentPageHelper() - clickOnCopyList()");
    }

    public void enterCopiedListName(String s) {
        log4j.startMethod("CurrentPageHelper() - enterCopiedListName()");
        log4j.info("wait until title field of copied list is clickable and click it");
        waitUntilElementIsClickable(copiedListTitleField, 10);
        copiedListTitleField.sendKeys(s);
        log4j.endMethod("CurrentPageHelper() - enterCopiedListName()");
    }

    public void clickOnCreateList() {
        log4j.startMethod("CurrentPageHelper() - clickOnCreateList()");
        log4j.info("click on 'Create' button of copied list");
        createCopiedListButton.click();
        log4j.endMethod("CurrentPageHelper() - clickOnCreateList()");
    }

//  delete
    public void archiveList() {
        log4j.startMethod("CurrentPageHelper() - archiveList()");
        log4j.info("intialize variable that shows quantity of lists in QA9 board");
        int beginListsQuantity = this.getListSize();
        clickOnExtraMenuButton();
        clickOnArchiveList();
        log4j.info("Cwait until the cards changes to -1");
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity-1,10);
        log4j.endMethod("CurrentPageHelper() - archiveList()");
    }

    public void clickOnArchiveList() {
        log4j.startMethod("CurrentPageHelper() - clickOnArchiveList()");
        log4j.info("wait until 'Archibe this list' is clickable and click it");
        waitUntilElementIsClickable(archieveThisListButton, 10);
        archieveThisListButton.click();
        log4j.endMethod("CurrentPageHelper() - clickOnArchiveList()");
    }

    public List<WebElement> getNameElements(By xpath) {
        return driver.findElements(xpath);
    }

    public void archiveNameList(int number) {
        log4j.startMethod("CurrentPageHelper() - archiveNameList()");
        log4j.info("intialize variable that shows quantity of lists in QA9 board");
        int beginLists = this.getListSize();
        clickOnExtraMenuButtonAccordingName(number);
        clickOnArchiveList();
        log4j.info("Cwait until the cards changes to -1");
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);
        log4j.endMethod("CurrentPageHelper() - archiveNameList()");
    }

    public void clickOnExtraMenuButtonAccordingName(int number) {
        log4j.startMethod("CurrentPageHelper() - clickOnExtraMenuButtonAccordingName()");
        log4j.info("wait until 'menu button' of the list is clickable and click it");
        waitUntilElementIsClickable(listMenuButton,5);
        log4j.info("find 'menu button' of the specific list and click it");
        driver.findElements(By.cssSelector(".list-header-extras-menu")).get(number).click();
        log4j.endMethod("CurrentPageHelper() - clickOnExtraMenuButtonAccordingName()");
    }

    public int getNumberOfElementWithName(String nameList) { //if there's no list with this name we'll get -1
        int number = -1;
        int counter = 0;
        for(WebElement element: listName){
            if(element.getText().equals(nameList)){
                number = counter;
            }
            counter++;
        }
        return number;
    }


    String thisListName = "Other List";

    public By getLocatorListName() {
        log4j.startMethod("CurrentPageHelper() - getLocatorListName()");
        return By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+ thisListName +"')]]");
    }

    public void archiveNameList1(String s) {
        List<WebElement> nameList = driver.findElements(getLocatorListName());
        int beginLists = this.getNameListSize();
        clickOnExtraMenuButtonAccordingName1(s);
        clickOnArchiveList();
        waitUntilElementsBecome(nameList,beginLists-1,5);
    }

    public int getNameListSize(){
        List<WebElement> nameList = driver.findElements(getLocatorListName());
        return nameList.size();
    }

    public void clickOnExtraMenuButtonAccordingName1(String s) {
        WebElement otherList = driver.findElement(getLocatorListName());
        waitUntilElementIsClickable(listMenuButton,5);
        otherList.findElement(By.cssSelector(".list-header-extras-menu")).click();
    }

    public boolean isCorrectPage() {
        return boardNameButton.getText().equals(boardName);
    }

    public String getNameBoard() {
        return boardName;
    }

//    public int getNumberOfListsBefore1(String s) {
//        int numberOfListsBefore = getListSize();
//        if (getNameListSize() == 0) {
//            createNewList(s);
//            numberOfListsBefore++;
//        }
//        return numberOfListsBefore;
//    }
}
