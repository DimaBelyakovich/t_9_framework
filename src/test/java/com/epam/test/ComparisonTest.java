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
        List<Item> addedItems = new ArrayList<>();

        CategoryPage categoryPage = new MainPage(driver)
                .openPage()
                .setupCity(city)
                .closePopUp()
                .goToCategory(type, name)
                .createItemList();

        addedItems.add(categoryPage.addToComparison(1));

        ItemPage itemPage = categoryPage.goToItem(2)
                                        .creatItem();
        addedItems.add(itemPage.addToComparison());

        ComparisonPage comparisonPage = itemPage
                                        .goToComparisonPageFromPopUp()
                                        .createItemsList();
        assertThat(comparisonPage.getItems(), is(equalTo(addedItems)));
    }
}
