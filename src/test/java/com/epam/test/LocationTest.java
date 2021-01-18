package com.epam.test;

import com.epam.page.MainPage;
import com.epam.service.FindContains;
import com.epam.service.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class LocationTest extends CommonConditions {
    private static final String CITY = "testdata.location.city";
    private static final String MONEY_FORMAT = "testdata.location.money";
    private static final String CATEGORY_TYPE = "testdata.category.type";
    private static final String CATEGORY_NAME = "testdata.category.name";

    @Test
    public void changeCityTest() throws UnsupportedEncodingException {
        String city = TestDataReader.getTestDataRus(CITY);
        String currency = TestDataReader.getTestDataRus(MONEY_FORMAT);
        String type = TestDataReader.getTestDataRus(CATEGORY_TYPE);
        String name = TestDataReader.getTestDataRus(CATEGORY_NAME);

        List<String> itemsCurrency = new MainPage(driver)
                                    .openPage()
                                    .setupCity(city)
                                    .closePopUp()
                                    .goToCategory(type, name)
                                    .getCurrency();

        Assert.assertTrue(FindContains.findContains(itemsCurrency, currency));

    }
}
