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

public class CreateAndDeleteBoardTest extends BaseTest {

    @BeforeTest
    public void setValues() {
        testName = "CreateAndDeleteBoardTest";
        testDescription = "User able to create new board";
        testCategory = "Smoke";
        testAuthor = "Indhumathi";
    }

    @Test
    public void createNewBoardTest() {
        BoardModel boardModel = new BoardModel("Folder for AutomationTest", "Board for automation");
        try {
            HomePage homePage = new HomePage();
            BoardPage boardPage = new BoardPage();
            FolderPage folderPage = new FolderPage();

            homePage.addFolder()
                    .enterTitle(boardModel.getFolderName())
                    .createBoard();
            boardPage.enterTitle(boardModel.getTitle())
                    .createColumn();
            homePage.switchToHome()
                    .deleteBoard(boardModel.getTitle())
                    .confirm();
            folderPage.verifyBoardIsNotDisplayed(boardModel.getTitle());

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
