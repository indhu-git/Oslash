package com.qlearly.regressions.tests.boards;

import com.qlearly.regressions.model.*;
import com.qlearly.regressions.pages.BoardPage;
import com.qlearly.regressions.pages.ColumnPage;
import com.qlearly.regressions.pages.FolderPage;
import com.qlearly.regressions.pages.HomePage;
import com.qlearly.regressions.tests.BaseTest;
import com.qlearly.regressions.utils.log.Log;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewBookMarkTest extends BaseTest {

    @BeforeTest
    public void setValues() {
        testName = "CreateNewBookMarkTest";
        testDescription = "User able to create new board";
        testCategory = "Smoke";
        testAuthor = "Indhumathi";

    }

    @Test
    public void createNewBoardTest() {
        BoardModel boardModel = new BoardModel("Folder for AutomationTest", "Board for automation");
        ColumnModel columnModel = new ColumnModel(boardModel, "Column for automation");
        columnModel.addLinkEntry("Google", "https://www.google.com");
        System.out.println(columnModel.getEntryList());
        try {
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
                    .addLinkEntry(columnModel.getEntryList().get(0))
                    .verifyEntryCount("1")
                    .open()
                    .switchTo_nthWindow(2)
                    .verifyCurrentUrl(columnModel.getEntryList().get(0).getLink());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
