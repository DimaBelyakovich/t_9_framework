package com.epam.test;

import com.epam.model.Item;
import com.epam.page.CategoryPage;
import com.epam.page.ComparisonPage;
import com.epam.page.ItemPage;
import com.epam.page.MainPage;
import com.epam.service.TestDataReader;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ComparisonTest extends CommonConditions{
    private static final String LOCATION_PROPERTY = "testdata.location.city";
    private static final String CATEGORY_TYPE1 = "testdata.category.type1";
    private static final String CATEGORY_NAME1 = "testdata.category.name1";

    @Test
    public void comparisonOneTypeWithoutAuthorizationTest() throws UnsupportedEncodingException {
        String city = TestDataReader.getTestDataRus(LOCATION_PROPERTY);
        String type = TestDataReader.getTestDataRus(CATEGORY_TYPE1);
        String name = TestDataReader.getTestDataRus(CATEGORY_NAME1);
        LinkedList<Item> addedItems = new LinkedList<>();

        ComparisonPage comparisonPage = new MainPage(driver)
                                    .openPage()
                                    .setupCity(city)
                                    .closePopUp()
                                    .goToCategory(type, name)
                                    .createItemList()
                                    .addToComparison(1, addedItems)
                                    .goToItem(2)
                                    .creatItem()
                                    .addToComparison(addedItems)
                                    .goToComparisonPageFromPopUp()
                                    .createItemsList();

        assertThat(comparisonPage.getPageTitle(), is(equalTo("Сравнение товаров")));
        assertThat(comparisonPage.getItems(), is(equalTo(addedItems)));
    }
}
