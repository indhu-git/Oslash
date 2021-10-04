package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.pages.wrappers.BasePage;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EntryPage extends BasePage {

    @FindBy(css = ".entryTitle")
    private WebElement title;

    @FindBy(css = ".cardaction")
    private WebElement threeDots;

    @FindBy(css = ".board-action")
    private WebElement boardOption;


    public String getTitle() {
        return getText(title);
    }

    public OptionsPage clickOnThreeDots() {
        try {
            click(threeDots);
            log("Options is opened", Status.PASS);
        } catch (Throwable t) {
            log("Unable to open Options", Status.FAIL);;
        }
        return new OptionsPage();
    }
}
