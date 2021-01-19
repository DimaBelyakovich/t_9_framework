package com.epam.test;

import com.epam.model.Item;
import com.epam.page.CategoryPage;
import com.epam.page.ItemPage;
import com.epam.page.MainPage;
import com.epam.page.WishListPage;
import com.epam.service.TestDataReader;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class WishItemsTest extends CommonConditions{
    private static final String LOCATION_PROPERTY = "testdata.location.city";
    private static final String CATEGORY_TYPE = "testdata.category.type";
    private static final String CATEGORY_NAME = "testdata.category.name";

    @Test
    public void wishItemsWithoutAuthorizationTest() throws UnsupportedEncodingException {
        String city = TestDataReader.getTestDataRus(LOCATION_PROPERTY);
        String type = TestDataReader.getTestDataRus(CATEGORY_TYPE);
        String name = TestDataReader.getTestDataRus(CATEGORY_NAME);
        LinkedList<Item> addedItems = new LinkedList<>();

        CategoryPage categoryPage = new MainPage(driver)
                                    .openPage()
                                    .setupCity(city)
                                    .closePopUp()
                                    .goToCategory(type, name)
                                    .createItemList()
                                    .addToWishList(1, addedItems);
        assertThat(categoryPage.getNumLikedSpan(), is(equalTo(addedItems.size())));

        ItemPage itemPage = categoryPage.goToItem(2)
                                        .creatItem()
                                        .addToWishList(addedItems);
        assertThat(itemPage.getNumLikedSpan(), is(equalTo(addedItems.size())));

        WishListPage wishListPage = itemPage
                                    .closePopUp()
                                    .goToWishListPage()
                                    .createItemsList();
        assertThat(wishListPage.getNumLikedSpan(), is(equalTo(addedItems.size())));
        assertThat(wishListPage.getPageTitle(), is(equalTo("Избранное")));
        assertThat(wishListPage.getItems(), is(equalTo(addedItems)));
    }
}
