package testcases;

import base.BaseClass;
import dataProvider.CustomDataProvider;
import helper.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.Map;

import static browserFactory.BrowserFactory.getDriver;

public class FiltersTestCase extends BaseClass {

    @BeforeMethod
    public void setupTest() {
        getDriver().get(ConfigReader.getProperty("url"));
    }


    @Test(dataProvider = "filtersData", dataProviderClass = CustomDataProvider.class)
    public void filtersTest(Map<String, String> data) {

        HomePage homePage = new HomePage();
        homePage.handleCookieConsent();
        ProductsPage productsPage = homePage.navigateToProducts("PARFUM");

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String filterName = entry.getKey();
            String filterValue = entry.getValue();
            productsPage.openFilter(filterName);
            productsPage.applyFilter(filterValue);
        }

//        Verifying Filters Applied
        for (String filterName : data.values()) {
            Assert.assertTrue(productsPage.isFilterApplied(filterName));
        }

    }

}
