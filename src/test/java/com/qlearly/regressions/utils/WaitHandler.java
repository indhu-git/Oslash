package com.qlearly.regressions.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class WaitHandler {

    WebDriver driver;
    private int waitTime = 120;

    public WaitHandler() {
        this.driver = DriverProvider.getDriver();
    }

    public WebElement waitForElementToDisplay(WebElement webElement) {
        return waitForElementToDisplay(webElement, waitTime);
    }

    public WebElement waitForElementToDisplay(WebElement webElement, long waitTime) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.visibilityOf(webElement));
    }

    public Boolean waitForElementToDisappear(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public WebElement waitForElementToBeClickable(WebElement webElement) {
        return waitForElementToBeClickable(webElement, waitTime);
    }

    public WebElement waitForElementToBeClickable(WebElement webElement, long waitTime) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForElementToBePresent(WebElement webElement) {
        waitForElementToBePresent(webElement, waitTime);
    }

    public void waitForElementToBePresent(WebElement webElement, long waitTime) {
        new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
               try {
                   webElement.isDisplayed();
                   return webElement;
               } catch (NoSuchElementException e) {
                    return null;
               }
            }
        });
    }

    public List<WebElement> waitForElementsToDisplay(List<? extends WebElement> webElements) {
        return waitForElementsToDisplay((List<WebElement>) webElements, waitTime);
    }

    public void waitForTitleToBeDisplay(String title) {
        new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.titleIs(title));
    }

    public WebElement waitForElement(By locator, int waitTime) {
        for (int i = 0; i < waitTime; i++) {
            try {
                if(driver.findElement(locator).isDisplayed())
                    return driver.findElement(locator);
            } catch (Exception e) {
                sleep(1000);
            }
        }
        return null;
    }

    public WebElement waitForElement(By locator) {
       return waitForElement(locator, waitTime);
    }

    public WebElement waitForElement(WebElement parentElement, By childElementLocator) {
        for (int i = 0; i < waitTime; i++) {
            try {
                if(parentElement.findElement(childElementLocator).isDisplayed())
                    return parentElement.findElement(childElementLocator);
            } catch (Exception e) {
                sleep(1000);
            }
        }
        return null;
    }

    public List<WebElement> waitForElementsToDisplay(List<? extends WebElement> webElements, long waitTime) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) webElements));
    }

    public void waitForPageToLoad() {
        waitForPageToLoad(waitTime);
    }

    public void waitForPageToLoad(int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds)).until((WebDriver) -> {
                return String.valueOf(executeJavascript("return document.readyState"))
                        .equals("complete");
            });
        } catch (TimeoutException ex) {
            // don't throw if page is still loading. Some pages never
            // archive readyState == complete, but are functionaly correct
        }
    }

    public void sleep(int timeInMilliSeconds) {
        executeScriptAsync("var callback = arguments[arguments.length-1]; setTimeout(function(){ callback() }, arguments[0]);", timeInMilliSeconds);
    }

    /* Execute Javascript */
    public Object executeJavascript(String script, Object... arguments) {
        return ((JavascriptExecutor) driver).executeScript(script, arguments);
    }

    public void executeScriptAsync(String script, Object... arguments) {
        ((JavascriptExecutor) driver).executeAsyncScript(script, arguments);
    }



    private By getElementBy(WebElement element) {
        String[] locatedBy = {};
        //element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]\\]", "$1" + "").split(":");

        //logger.info(element.toString());
        if (locatedBy.length > 1) {

            switch (locatedBy[0].trim()) {
                case "css selector":
                    return By.cssSelector(locatedBy[1]);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public void sleep(long waitTime){
        try{
            Thread.sleep(waitTime);
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
