package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.pages.wrappers.BasePage;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class FolderPage extends BasePage {

    @FindBy(name = "title")
    private List<WebElement> title;

    @FindBy(xpath = ".//*[contains(text(), 'Create Board')]")
    private WebElement createBoard;

    @FindBy(id = ".//button[text()='Create']")
    private WebElement createButton;

    @FindBy(css = ".boards-container div.col-md-4 h4 a")
    private List<WebElement> boardList;


    public String getTitle() {
        return getText(title.get(0));
    }

    public FolderPage enterTitle(String titleValue) {
        try {
            click(title.get(0));
            clearAndType(title.get(0), titleValue);
            log("Folder title is entered as " + titleValue, Status.PASS);
        } catch (Throwable t) {
            t.printStackTrace();
            log("Unable to enter folder title as " + titleValue, Status.FAIL);
        }
        return this;
    }

    public FolderPage createBoard() {
        try {
            click(createBoard);
            log("Create board is clicked.", Status.PASS);
        } catch (Throwable t) {
            t.printStackTrace();
            log("Unable to click Create board", Status.FAIL);
        }
        return this;
    }

    public FolderPage createButton() {
        try {
            click(createButton);
            log("Create button is clicked.", Status.PASS);
        } catch (Throwable t) {
            t.printStackTrace();
            log("Unable to click Create button", Status.FAIL);
        }
        return new FolderPage();
    }

    public BoardPage openBoard(String boardName) {
        try {
            scrollIntoViewAndClick(getBoard(boardName));
            log(boardName + " board is opened.", Status.PASS);
        } catch (Throwable t) {
            t.printStackTrace();
            log("Unable to open board", Status.FAIL);
        }
        return new BoardPage();
    }

    public BoardPage verifyBoardIsNotDisplayed(String boardName) {
        try {
            List<WebElement> list = boardList.stream().filter(element -> element.getText().equals(boardName)).collect(Collectors.toList());
            Assert.assertTrue(list.isEmpty());
            log(boardName + " board is no available.", Status.PASS);
        } catch (Throwable t) {
            t.printStackTrace();
            log("Board is available with name "+boardName, Status.FAIL);
        }
        return new BoardPage();
    }
    public DeleteModal deleteBoard(String boardName) {
        try {
            scrollIntoViewAndClick(getBoard(boardName));
            log(boardName + " board is opened.", Status.PASS);
        } catch (Throwable t) {
            t.printStackTrace();
            log("Unable to open board", Status.FAIL);
        }
        return new DeleteModal();
    }

    public WebElement getBoard(String boardName) {
        return boardList.stream().filter(b -> b.getText().equals(boardName)).collect(Collectors.toList()).get(0);
    }
}
