package pages;

import helper.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static browserFactory.BrowserFactory.getDriver;

public class ProductsPage {

    private static final Logger Log = LogManager.getLogger(ProductsPage.class.getName());
    By sortBtn = By.cssSelector("div.sort-facet");
    By appliedFilters = By.cssSelector(".selected-facets__value");
    By productBrand = By.cssSelector(".text.text.top-brand");
    By productCategory = By.cssSelector(".text.category");

    public void applyMultipleFilters(Map<String, String> filters) {
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String filterName = entry.getKey();
            String filterValue = entry.getValue();
            openFilter(filterName);
            applyFilter(filterValue);
        }
    }

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


    public String getFirstProductBrand() {
        return Utility.waitForElementVisible(productBrand, 10).getText();
    }

    public String getFirstProductCategory() {
        return Utility.waitForElementVisible(productCategory, 10).getText();
    }

    public boolean isFilterInMap(Collection<String> filterValuesFromMap, String value) {
        boolean valueInMap = false;

        for (String filterValue : filterValuesFromMap) {
            if (filterValue != null && filterValue.equalsIgnoreCase(value)) {
                valueInMap = true;
                break;
            }
        }

        return valueInMap;
    }

}
