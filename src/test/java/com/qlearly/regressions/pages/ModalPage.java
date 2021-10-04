package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.pages.wrappers.BasePage;
import com.qlearly.regressions.utils.DriverProvider;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalPage extends BasePage {

    @FindBy(id = "entry_title")
    private WebElement title;

    @FindBy(id = "entry_website_url")
    private WebElement url;

    @FindBy(xpath = ".//button[contains(text(), 'CREATE')]")
    private WebElement create;

    public ModalPage enterTitle(String titleValue) {
        try {
            clearAndType(title, titleValue);
            log("Title is entered", Status.PASS);
        } catch (Throwable t) {
            log("Unable to enter title", Status.FAIL);
        }
        return this;
    }

    public ModalPage enterUrl(String urlValue) {
        try {
            clearAndType(url, urlValue);
            log("URL is entered", Status.PASS);
        } catch (Throwable t) {
            log("Unable to enter url", Status.FAIL);
        }
        return this;
    }

    public ColumnPage create() {
        try {
            click(create);
            log("Create is clicked", Status.PASS);
        } catch (Throwable t) {
            log("Unable click create", Status.FAIL);
        }
        return new ColumnPage();
    }
}
