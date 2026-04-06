package com.multibank.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "screenshots/";

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String fileName = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";
            Path destination = Paths.get(fileName);
            Files.copy(srcFile.toPath(), destination);
            return new File(fileName).getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return "screenshots/default.png";
        }
    }
}