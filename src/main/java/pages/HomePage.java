package pages;

import helper.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static browserFactory.BrowserFactory.getDriver;

public class HomePage {

    private static final Logger Log = LogManager.getLogger(HomePage.class.getName());
    private final By shadowHost = By.id("usercentrics-root");
    private final By acceptAllCookiesBtn = By.cssSelector("button[data-testid='uc-accept-all-button']");

    public void handleCookieConsent() {
        try {
            Utility.waitForPresenceOfElement(shadowHost, 30);
            WebElement cookieAcceptBtn = Utility.getShadowElement(shadowHost, acceptAllCookiesBtn);
            cookieAcceptBtn.click();
            Log.info("Cookie consent accepted.");
        } catch (Exception e) {
            Log.info("Cookie consent popup not found. Proceeding with the test.");
        }
    }

    public ProductsPage navigateToProducts(String tabName) {
        getDriver().findElement(By.xpath("//li/a[contains(text(),'" + tabName + "')]")).click();
        return new ProductsPage();
    }


}
