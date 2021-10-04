package com.qlearly.regressions.utils;

import com.qlearly.regressions.reporter.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Interactions extends Reporter {

    public WebDriver driver;
    public WaitHandler wait;

    public Interactions() {
        driver = DriverProvider.getDriver();
        wait = new WaitHandler();
    }

    public void click(WebElement element) {
        wait.waitForElementToBeClickable(element)
                .click();
    }

    public void mouseHover(WebElement element) {
        wait.waitForElementToBeClickable(element);
        new Actions(driver)
                .moveToElement(element);
    }

    public void doubleClick(WebElement element) {
        wait.waitForElementToBeClickable(element);
        new Actions(driver)
                .doubleClick(element);
    }

    public void type(WebElement element, String value) {
        wait.waitForElementToDisplay(element)
                .sendKeys(value);
    }

    public void clearAndType(WebElement element, String value) {
        wait.waitForElementToDisplay(element)
                .clear();
        element.sendKeys(value);
    }

    public String getText(WebElement element) {
        return wait.waitForElementToDisplay(element)
                .getText();
    }

    public String getAttribute(WebElement element, String attributeName) {
        return wait.waitForElementToDisplay(element)
                .getAttribute(attributeName);
    }

    public void scrollIntoViewAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(); arguments[0].click()", element);
    }


    public int takeSnap() {
        int ranNum = (int) (Math.random() * 99999);
        try {
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
            ImageIO.write(myScreenshot.getImage(), "PNG", new File(System.getProperty("user.dir")+"/reports/snaps/img" + ranNum + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ranNum;
    }
}

