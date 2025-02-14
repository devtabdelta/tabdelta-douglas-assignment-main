package dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider(name = "filtersData")
    public static Object[][] getData() {
        return ExcelReader.readExcelDataWithHeaders("filters", System.getProperty("user.dir") + "/testdata/Filters.xlsx");
    }

}
