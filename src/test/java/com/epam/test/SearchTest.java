package com.epam.test;

import com.epam.page.CategoryPage;
import com.epam.page.MainPage;
import com.epam.service.FindContains;
import com.epam.service.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchTest extends CommonConditions{
    private static final String LOCATION_PROPERTY = "testdata.location.city";
    private static final String QUERY_PROPERTY = "testdata.search.category";
    private static final String SEARCHED_ITEM_TITLE = "testdata.search.itemtitle";

    @Test
    public void searchResultCategoryTest() throws UnsupportedEncodingException {
        String city = TestDataReader.getTestDataRus(LOCATION_PROPERTY);
        String searchedCategory = TestDataReader.getTestDataRus(QUERY_PROPERTY);
        String expectedItemTitle = TestDataReader.getTestDataRus(SEARCHED_ITEM_TITLE);


        CategoryPage categoryPage = new MainPage(driver)
                                    .openPage()
                                    .setupCity(city)
                                    .closePopUp()
                                    .search(searchedCategory);

        String pageTitle = categoryPage.getCategoryTitle();
        assertThat(pageTitle, is(equalTo(searchedCategory)));

        List<String> itemTitles = categoryPage.getSearchResultTitles();
        Assert.assertTrue(FindContains.findContains(itemTitles, expectedItemTitle));


    }
}
