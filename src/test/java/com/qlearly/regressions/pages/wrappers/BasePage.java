package com.qlearly.regressions.pages.wrappers;

import com.aventstack.extentreports.Status;
import com.qlearly.regressions.utils.DriverProvider;
import com.qlearly.regressions.utils.Interactions;
import com.qlearly.regressions.utils.log.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.util.Set;

public class BasePage extends Interactions {

    protected WebDriver driver;

    public BasePage() {
        super();
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }

    public BasePage switchTo_nthWindow(Integer nthWindow) {
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            int i = 1;
            for (String win : windowHandles) {
                if (i == nthWindow) {
                    driver.switchTo().window(win);
                    break;
                }
                i++;
            }
           log("Switched to " + nthWindow + " window", Status.PASS);
        } catch (Throwable t) {
            log("Unable to switch window.", Status.FAIL);
        }
        return this;
    }

    public BasePage verifyCurrentUrl(String url) {
        try {
            Assert.assertTrue(driver.getCurrentUrl().contains(url));
           log("Current url is matched", Status.PASS);
        } catch (Throwable t) {
            log("Current url is incorrect", Status.FAIL);
        }
        return this;
    }
}
