package com.qlearly.regressions.tests.boards;

import com.qlearly.regressions.model.BoardModel;
import com.qlearly.regressions.model.ColumnModel;
import com.qlearly.regressions.pages.*;
import com.qlearly.regressions.tests.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteBookMarkTest extends BaseTest {

    BoardModel boardModel = new BoardModel("Folder for AutomationTest", "Board for automation");
    ColumnModel columnModel = new ColumnModel(boardModel, "Column for automation");

    @BeforeTest
    public void setValues() {
        testName = "DeleteBookMarkTest";
        testDescription = "User able to delete bookmark";
        testCategory = "Smoke";
        testAuthor = "Indhumathi";

    }

    @Test(description = "User able to delete bookmark")
    public void deleteBookMark() {
        columnModel.addLinkEntry("Google", "https://www.google.com");
        try {
            preRequisite();
            new ColumnPage()
                    .openOption(columnModel.getEntryList().get(0).getTitle())
                    .clickOptionElement(Options.DELETE);
            new ColumnPage()
                    .verifyEntryCount("0")
                    .verifyEntryList("0");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void preRequisite() {
        new HomePage()
                .addFolder()
                .enterTitle(boardModel.getFolderName())
                .createBoard();
        new BoardPage()
                .enterTitle(boardModel.getTitle())
                .createColumn();
        new FolderPage()
                .openBoard(boardModel.getTitle())
                .verifyBoardName(boardModel.getTitle());
        new ColumnPage()
                .addLinkEntry(columnModel.getEntryList().get(0));
    }
}
