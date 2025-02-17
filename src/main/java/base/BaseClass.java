package base;

import browserFactory.BrowserFactory;
import enums.Browsers;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseClass {

    @Parameters({"browser", "headless", "runOnRemote"})
    @BeforeMethod
    public void setupApplication(String browser, boolean headless, boolean runOnRemote, ITestResult result) {
        try {
            BrowserFactory.createDriver(Browsers.valueOf(browser.toUpperCase()), headless, runOnRemote, result);
        } catch (IllegalArgumentException e) {
            BrowserFactory.createDriver(Browsers.valueOf("CHROME"), false, false, result);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
