package com.epam.page;

import com.epam.model.Item;
import com.epam.waits.CustomWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends MainPage{

    @FindBy(xpath = "//h3[@class='_3dCGE8Y9v3 cLo1fZHm2y']")
    private List<WebElement> itemsTitle;

    private List<Item> itemsOnPage;

    public WishListPage(WebDriver driver) {
        super(driver);
        this.itemsOnPage = new ArrayList<>();
        CustomWaits.waitForPageLoaded(driver);
        logger.info("WishList page created");
    }

    public WishListPage createItemsList(){
        for (int i = 0; i < itemsTitle.size(); i++) {
            this.itemsOnPage.add(new Item(itemsTitle.get(i).getText(), true));
        }
        return this;
    }

    public List<Item> getItems(){
        return this.itemsOnPage;
    }
}
