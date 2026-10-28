package com.khushbu.automation.listeners;

import com.khushbu.automation.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {
    @Override public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (!(driver instanceof TakesScreenshot)) return;
            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String name = result.getMethod().getMethodName() + "-" + stamp + ".png";
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(source.toPath(), dir.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            System.err.println("Failure screenshot saved: " + dir.resolve(name).toAbsolutePath());
        } catch (Exception e) { System.err.println("Could not capture screenshot: " + e.getMessage()); }
    }
}