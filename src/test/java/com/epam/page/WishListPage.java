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

    @FindBy(xpath = "//h1[@class='_1hhP4ZCq0d']")
    private WebElement pageTitle;

    private List<Item> itemsOnPage;

    public WishListPage(WebDriver driver) {
        super(driver);
        this.itemsOnPage = new ArrayList<>();
        CustomWaits.waitForPageLoaded(driver);
        logger.info("WishList page created");
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public WishListPage createItemsList(){
        for (int i = 0; i < itemsTitle.size(); i++) {
            Item item = new Item(itemsTitle.get(i).getText());
            item.setFavourite(true);
            this.itemsOnPage.add(item);
        }
        return this;
    }

    public List<Item> getItems(){
        return this.itemsOnPage;
    }
}
