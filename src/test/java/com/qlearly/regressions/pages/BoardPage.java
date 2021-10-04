package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.pages.wrappers.BasePage;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class BoardPage extends BasePage {

    @FindBy(name = "boardname")
    private WebElement title;

    @FindBy(name = "make_favorite")
    private WebElement makeFavorite;

    @FindBy(css = ".personalBoard_section")
    private WebElement threeDots;

    @FindBy(css = "board-action")
    private WebElement threeDotsOptionContainer;

    @FindBy(id = "create_column_btn")
    private WebElement createColumn;

    @FindBy(xpath = ".//*[contains(@id, 'column')]")
    private List<WebElement> columnList;

    @FindBy(css = ".board-action")
    private WebElement optionsContainer;

    @FindBy(id = "selected_board_name")
    private WebElement selectedBoardName;

    @FindBy(xpath = ".//*[text()='Create']")
    private WebElement createButton;


    public String getBoardTitle() {
        return getText(title);
    }

    public BoardPage enterTitle(String titleValue) {
        try {
            clearAndType(title, titleValue);
            log("Board title is entered", Status.PASS);
        } catch (Throwable t) {
            log("Unable to enter board title", Status.FAIL);
        }
        return this;
    }

    public BoardPage makeFavorite() {
        try {
            click(title);
            log("Favorite is clicked", Status.PASS);
        } catch (Throwable t) {
            log("Unable to favorite", Status.FAIL);
        }
        return this;
    }


    public OptionsPage openOptions() {
        try {
            click(threeDotsOptionContainer);
            log("Three dots is clicked", Status.PASS);
        } catch (Throwable t) {
            log("Unable to click three dots", Status.FAIL);
        }
        return new OptionsPage();
    }

    public ColumnPage createColumn() {
        try {
            click(createButton);
            log("create column is clicked", Status.PASS);
        } catch (Throwable t){
            log("Unable to click create column", Status.FAIL);
        }
        return new ColumnPage();
    }

    public String getSelectedBoardName(){
        return getAttribute(selectedBoardName, "value");
    }

    public BoardPage verifyBoardName(String boardName) {
        try{
            Assert.assertEquals(getSelectedBoardName(), boardName);
            log("Board is displayed as expected", Status.PASS);
        }catch (Throwable t){
            log("Board name is incorrect", Status.FAIL);
        }
        return new BoardPage();
    }


    public BoardPage deleteBoard(String boardName) {
        try{
            openOptions()
                    .deleteBoard(boardName);
            log("Board is deleted", Status.PASS);
        }catch (Throwable t){
            log("Unable to delete board", Status.FAIL);
        }
        return new BoardPage();
    }
}
