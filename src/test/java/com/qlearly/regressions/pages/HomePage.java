package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.pages.wrappers.BasePage;
import com.qlearly.regressions.utils.DriverProvider;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = ".//h4[text()='Add Folder']")
    private WebElement addFolder;

    @FindBy(css = ".board-row")
    private List<WebElement> boardList;

    @FindBy(css = ".ml-auto")
    private WebElement homeButton;

    @FindBy(css = ".//*[contains(text(), 'Account Settings')]")
    private WebElement accountSettings;

    public HomePage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    public FolderPage addFolder(){
        try{
            click(addFolder);
            log("Add Folder is clicked.", Status.PASS);
        }catch (Throwable t){
            t.printStackTrace();
            log("Unable to click add folder", Status.FAIL);
        }
        return new FolderPage();
    }

    public FolderPage switchToHome(){
        try{
            click(homeButton);
            log("Home icon is clicked.", Status.PASS);
        }catch (Throwable t){
            t.printStackTrace();
            log("Unable to click home icon", Status.FAIL);
        }
        return new FolderPage();
    }

}
