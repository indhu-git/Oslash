package com.qlearly.regressions.reporter;

import java.io.File;
import java.io.IOException;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qlearly.regressions.utils.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public abstract class Reporter {
    public static ExtentHtmlReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest test, node;

    public String testName, testDescription, testCategory, testAuthor;

    @BeforeSuite
    public void startReport() {
        FileUtils.clearDirectory(new File(System.getProperty("user.dir")+"/reports"));
        reporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/reports/result.html"));
        reporter.setAppendExisting(true);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeClass
    public void testcaseDetails() {
        test = extent.createTest(testName, testDescription);
        test.assignCategory(testCategory);
        test.assignAuthor(testAuthor);

    }

    public abstract int takeSnap() throws IOException;

    public void log(String msg, Status status) {
        if (status.equals(Status.PASS)) {
            try {
                node.pass(msg,
                        MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"/reports/snaps/img" + takeSnap() + ".png").build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (status.equals(Status.FAIL)) {
            try {
                node.fail(msg,
                        MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"/reports/snaps/img" + takeSnap() + ".png").build());
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new RuntimeException();
        }
    }

    @AfterSuite
    public void endReport() {
        extent.flush();

    }

}