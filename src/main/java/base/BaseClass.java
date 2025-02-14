package base;

import browserFactory.BrowserFactory;
import enums.Browsers;
import org.testng.annotations.*;

public class BaseClass {

    @Parameters ({"browser", "headless"})
    @BeforeMethod
    public void setupApplication(String browser, boolean headless) {
        try {
            BrowserFactory.createDriver(Browsers.valueOf(browser.toUpperCase()), headless);
        } catch (IllegalArgumentException e) {
            BrowserFactory.createDriver(Browsers.valueOf("CHROME"),false);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
