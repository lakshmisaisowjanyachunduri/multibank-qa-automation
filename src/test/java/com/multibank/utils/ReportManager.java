package com.multibank.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/TestReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MultiBank QA Test Report");
        spark.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "MultiBank Trading Platform");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("URL", "https://trade.multibank.io/");
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}