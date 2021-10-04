package com.qlearly.regressions.utils.log;

import com.qlearly.regressions.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

public class JSErrorLogs {
    public static LogEntries getLogs() {
        return DriverProvider.getDriver().manage().logs().get(LogType.BROWSER);
    }
    public static Boolean isLoginErrorLog(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries.getAll().stream()
                .anyMatch(logEntry -> logEntry.getMessage().contains("An invalid email address was specified"));
    }
}
