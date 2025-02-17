package browserFactory;


import enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static final ThreadLocal<WebDriver> Driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return Driver.get();
    }

    private static final Logger Log = LogManager.getLogger(BrowserFactory.class.getName());

    public static void createDriver(final Browsers browser, boolean headless, boolean runOnRemote, ITestResult result) {
        if (runOnRemote) {
            try {
                setupSauceLabsDriver(browser, result);
            } catch (MalformedURLException e) {
                Log.error("Error setting up BrowserStack driver: {}", e.getMessage());
                throw new RuntimeException("Failed to create BrowserStack driver", e);
            }
        } else {
            switch (browser) {
                case CHROME:
                    setupChromeDriver(headless);
                    break;
                case FIREFOX:
                    setupFirefoxDriver(headless);
                    break;
                case EDGE:
                    setupEdgeDriver(headless);
                    break;
            }
        }
        Log.info("Driver Created Successfully....");
        setupBrowserTimeouts();
    }

    public static void setupChromeDriver(boolean headless) {
        Log.info("Setting up Chrome Driver....");
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        Driver.set(driver);
    }

    public static void setupFirefoxDriver(boolean headless) {
        Log.info("Setting up Firefox Driver....");
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        Driver.set(driver);
    }

    public static void setupEdgeDriver(boolean headless) {
        Log.info("Setting up Edge Driver....");
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        Driver.set(driver);
    }

    public static void setupSauceLabsDriver(Browsers browser, ITestResult result) throws MalformedURLException {
        Log.info("Setting up Sauce Labs Driver....");

        WebDriver driver = null;

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName(System.getenv("SAUCE_PLATFORM"));
                chromeOptions.setBrowserVersion(System.getenv("SAUCE_BROWSER_VERSION"));
                Map<String, Object> sauceOptionsChrome = new HashMap<>();
                sauceOptionsChrome.put("username", System.getenv("SAUCE_USERNAME"));
                sauceOptionsChrome.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
                sauceOptionsChrome.put("build", System.getenv("SAUCE_BUILD"));
                sauceOptionsChrome.put("name", result.getMethod().getMethodName());
                chromeOptions.setCapability("sauce:options", sauceOptionsChrome);
                driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), chromeOptions);
                break;

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(System.getenv("SAUCE_PLATFORM"));
                firefoxOptions.setBrowserVersion(System.getenv("SAUCE_BROWSER_VERSION"));
                Map<String, Object> sauceOptionsFirefox = new HashMap<>();
                sauceOptionsFirefox.put("username", System.getenv("SAUCE_USERNAME"));
                sauceOptionsFirefox.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
                sauceOptionsFirefox.put("build", System.getenv("SAUCE_BUILD"));
                sauceOptionsFirefox.put("name", result.getMethod().getMethodName());
                firefoxOptions.setCapability("sauce:options", sauceOptionsFirefox);
                driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com:443/wd/hub"), firefoxOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName(System.getenv("SAUCE_PLATFORM"));
                edgeOptions.setBrowserVersion(System.getenv("SAUCE_BROWSER_VERSION"));
                Map<String, Object> sauceOptionsEdge = new HashMap<>();
                sauceOptionsEdge.put("username", System.getenv("SAUCE_USERNAME"));
                sauceOptionsEdge.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
                sauceOptionsEdge.put("build", System.getenv("SAUCE_BUILD"));
                sauceOptionsEdge.put("name", result.getMethod().getMethodName());
                edgeOptions.setCapability("sauce:options", sauceOptionsEdge);
                driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), edgeOptions);
                break;
        }

        Driver.set(driver); // Set the driver in the ThreadLocal
    }

    public static void quitDriver() {
        if (null != Driver.get()) {
            Log.info("Closing the driver...");
            getDriver().quit();
            Driver.remove();
        }
    }

    private static void setupBrowserTimeouts() {
        Log.info("Setting Browser Timeouts....");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
    }
}
