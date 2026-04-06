package com.multibank.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.multibank.utils.ConfigReader;
import com.multibank.utils.DriverManager;
import com.multibank.utils.ReportManager;
import com.multibank.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentTest extentTest;

    @BeforeSuite
    public void setUpSuite() {
        ReportManager.initReport();
    }

    @BeforeMethod
    public void setUp(Method method) {
        String browser = ConfigReader.get("browser");
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();

        extentTest = ReportManager.createTest(
                method.getName(),
                "Test: " + method.getName()
        );
        extentTest.log(Status.INFO, "Browser: " + browser);
        extentTest.log(Status.INFO, "Base URL: " + ConfigReader.get("base.url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
            extentTest.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "Test Passed Successfully");
        } else {
            extentTest.log(Status.SKIP, "Test Skipped");
        }
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void tearDownSuite() {
        ReportManager.flushReport();
    }
}