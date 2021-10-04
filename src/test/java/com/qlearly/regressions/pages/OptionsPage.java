package com.qlearly.regressions.pages;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.utils.DriverProvider;
import com.qlearly.regressions.utils.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

public class OptionsPage extends BoardPage {


    public WebElement getBoardOption(String optionName) {
        return DriverProvider.getDriver().findElement(By.xpath(".//*[contains(text(), '" + optionName + "')]"));
    }

    public void clickOptionElement(Options options) {
        try {
            WebElement element = driver.findElements(By.xpath(".//a[contains(text(), '" + options.option + "')]"))
                    .stream().filter(element1 -> element1.isDisplayed()).collect(Collectors.toList()).get(0);
            scrollIntoViewAndClick(element);
            log(options.option+" is clicked", Status.PASS);
        }catch (Throwable t){
            log("Unable to click "+options.option, Status.PASS);
        }
    }

}
