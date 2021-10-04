package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.pages.wrappers.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteModal extends BasePage {

    @FindBy(id = "confirmed_to_action")
    private WebElement confirmButton;

    public BoardPage confirm() {
        try{
            click(confirmButton);
            log("Confirm button is deleted", Status.PASS);
        }catch (Throwable t){
            log("Unable to confirm button", Status.FAIL);
        }
        return new BoardPage();
    }
}
