package com.qlearly.regressions.tests.boards;

import com.qlearly.regressions.model.BoardModel;
import com.qlearly.regressions.model.ColumnModel;
import com.qlearly.regressions.pages.BoardPage;
import com.qlearly.regressions.pages.ColumnPage;
import com.qlearly.regressions.pages.FolderPage;
import com.qlearly.regressions.pages.HomePage;
import com.qlearly.regressions.tests.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateBunchOfTabsTest extends BaseTest {

    @BeforeTest
    public void setValues() {
        testName = "CreateBunchOfTabsTest";
        testDescription = "User able to create bunch of tabs and able to switch to particular tab";
        testCategory = "Smoke";
        testAuthor = "Indhumathi";

    }

    @Test
    public void createNewBoardTest() {
        BoardModel boardModel = new BoardModel("Folder for AutomationTest", "Board for automation");
        ColumnModel columnModel = new ColumnModel(boardModel, "Column for automation");
        columnModel.addLinkEntry("Google", "https://www.google.com");
        columnModel.addLinkEntry("Facebook", "https://www.facebook.com");
        columnModel.addLinkEntry("Instagram", "https://www.instagram.com/");
        columnModel.addLinkEntry("Oslash", "https://www.oslash.com/");
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
                    .addLinkEntry(columnModel.getEntryList())
                    .open()
                    .switchTo_nthWindow(4);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
