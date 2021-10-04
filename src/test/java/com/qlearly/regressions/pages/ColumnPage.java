package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.model.EntryModel;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class ColumnPage extends BoardPage {

    @FindBy(id = "dropdownMenuLink")
    private WebElement threeDots;

    @FindBy(xpath = ".//button[text()='Add']")
    private WebElement add;

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement save;

    @FindBy(xpath = ".//button[text()='Open']")
    private WebElement open;

    @FindBy(css = ".column-input")
    private WebElement title;

    @FindBy(css = ".entry-count")
    private WebElement entryCount;

    @FindBy(css = "ul.entry-list li")
    private List<WebElement> entries;

    public String getTitle() {
        return getAttribute(title, "value");
    }

    public ModalPage addEntry() {
        try {
            click(add);
            log("Add is clicked", Status.PASS);
        } catch (Throwable t) {
            log("Unable to add entries", Status.FAIL);
        }
        return new ModalPage();
    }

    public ColumnPage addLinkEntry(EntryModel entryModel) {
        return addEntry()
                .enterTitle(entryModel.getTitle())
                .enterUrl(entryModel.getLink())
                .create();
    }

    public ColumnPage addLinkEntry(List<EntryModel> entryModelList) {
        for (EntryModel entryModel: entryModelList){
            addLinkEntry(entryModel);
        }
        return this;
    }
    public ColumnPage save() {
        try {
            click(save);
            log("Save is clicked", Status.PASS);
        } catch (Throwable t) {
            log("Unable to save entries", Status.FAIL);
        }
        return this;
    }

    public ColumnPage open() {
        try {
            click(open);
            log("Open is clicked", Status.PASS);
        } catch (Throwable t) {
            log("Unable to open entries", Status.FAIL);;
        }
        return this;
    }

    public ColumnPage enterTitle(String value) {
        try {
            clearAndType(open, value);
            log("Column title is entered", Status.PASS);
        } catch (Throwable t) {
            log("Unable to enter title", Status.FAIL);
        }
        return this;
    }

    public String getEntryCount() {
        return getText(entryCount);
    }

    public ColumnPage verifyEntryCount(String count) {
        try {
            Assert.assertEquals(getEntryCount(), count + " Entry");
            log("Entry count is displayed as expected", Status.PASS);
        } catch (Throwable t) {
            log("Entry is incorrect", Status.FAIL);
        }
        return new ColumnPage();
    }


    public ColumnPage verifyEntryList(String count) {
        try {
            Assert.assertEquals(entries.size(), Integer.parseInt(count));
            log(count+" number entries is displayed", Status.PASS);
        } catch (Throwable t) {
            log("Entry is incorrect", Status.FAIL);
        }
        return new ColumnPage();
    }

    public OptionsPage openOption(String entryTitle) {
        try {
            WebElement entry = entries.stream().filter(element ->
                    element.findElement(By.cssSelector(".entryTitle")).getText()
                            .equals(entryTitle)).collect(Collectors.toList()).get(0);
            mouseHover(entry);
            scrollIntoViewAndClick(entry.findElement(By.cssSelector("#dropdownMenuLink div")));
            log("Options is opened for entry "+entryTitle, Status.PASS);
        }catch (Throwable t){
            log("Unable to open option", Status.FAIL);
        }
        return new OptionsPage();
    }

}
