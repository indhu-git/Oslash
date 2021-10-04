package com.qlearly.regressions.tests;

import com.qlearly.regressions.utils.DriverProvider;
import com.qlearly.regressions.utils.Interactions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest extends Interactions {

    @BeforeMethod
    public void setup() {
        node = test.createNode(testName);
        DriverProvider.setLocally();
        driver = DriverProvider.getDriver();
        driver.get("chrome-extension://pgoflfgdgcmjcbhgcfjffcaeibhipmkd/index.html");
        closeAllChildWindows();
        driver.get("chrome-extension://pgoflfgdgcmjcbhgcfjffcaeibhipmkd/index.html");
    }

    @AfterMethod
    public void tearDown() {
        DriverProvider.getDriver().quit();
    }

    public void closeAllChildWindows() {
        String parent = "";
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            if (driver.getCurrentUrl() == "chrome-extension://pgoflfgdgcmjcbhgcfjffcaeibhipmkd/index.html") {
                parent = winHandle;
            } else {
                driver.close();
            }
            if (driver.getWindowHandles().size() == 1) {
                driver.switchTo().window(parent);
                break;
            }
        }
    }
}
