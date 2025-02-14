package pages;

import helper.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static browserFactory.BrowserFactory.getDriver;

public class ProductsPage {

    private static final Logger Log = LogManager.getLogger(ProductsPage.class.getName());
    By sortBtn = By.cssSelector("div.sort-facet");
    By appliedFilters = By.cssSelector(".selected-facets__value");

    public void openFilter(String filterName) {
        Utility.waitForElementClickable(By.xpath("//div[contains(text(),'" + filterName + "')]"), 10).click();
    }

    public void applyFilter(String filterQuery) {
        getDriver().findElement(By.xpath("//div[text()='" + filterQuery + "']/../.. //input")).click();
        Utility.waitForElementVisible(sortBtn, 15);
    }

    public boolean isFilterApplied(String expectedFilter) {
        List<WebElement> filters = getDriver().findElements(appliedFilters);
        boolean textFound = false;

        for (WebElement actualFilter : filters) {
            String actualText = actualFilter.getText();
            if (actualText.toLowerCase().contains(expectedFilter.toLowerCase())) {
                textFound = true;
                break;
            }
        }

        return textFound;
    }

}
