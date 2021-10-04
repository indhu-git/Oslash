package com.qlearly.regressions.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class DriverProvider {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void setDriver(String browser) {
        try {
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), getCapabilities(browser)));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }catch (Throwable t){

        }
    }

    public static void setLocally() {
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(System.getProperty("user.dir")+"/src/test/resources/app.crx"));
            driver.set(new ChromeDriver(options));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }catch (Throwable t){
            t.printStackTrace();
        }
    }


    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().close();
        driver.remove();
    }


    public static Capabilities getCapabilities(String browser) {
        Capabilities capabilities = null;
        if (browser.equals("chrome"))
            capabilities = getChromeOptions();
        return capabilities;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addExtensions(new File(System.getProperty("user.dir")+"/src/test/resources/resources/app.crx"));
        return options;
    }
}
