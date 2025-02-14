package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static browserFactory.BrowserFactory.getDriver;

public class Utility {
    private static final Logger Log = LogManager.getLogger(Utility.class.getName());

    public static void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    public static void captureScreenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File("./screenshots/Screenshot_" + getCurrentDateTime() + ".png");
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            Log.info(e.getMessage());
        }
    }

    public static String captureScreenshotAsBase64() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        String src = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        return src;
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss").format(new Date());
    }

    public static void captureElementScreenshot(WebElement element) {
        File src = element.getScreenshotAs(OutputType.FILE);
        File dest = new File("./screenshots/Screenshot_" + getCurrentDateTime() + ".png");
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            Log.info(e.getMessage());
        }
    }

    public static WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForPresenceOfElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement getShadowElement(By shadowHostLocator, By elementLocator) {
        WebElement shadowHost = getDriver().findElement(shadowHostLocator);
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        return shadowRoot.findElement(elementLocator);
    }

}
