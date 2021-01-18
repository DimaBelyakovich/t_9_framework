package com.epam.page;

import com.epam.model.Item;
import com.epam.waits.CustomWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends MainPage {

    @FindBy(xpath = "//h1[@class='_3wPGpzKmmn']")
    private WebElement pageTitle;

    @FindBy(xpath = "//h3[@class='_3dCGE8Y9v3 cLo1fZHm2y']")
    private List<WebElement> itemsTitle;

    @FindBy(xpath = "//div[@class='_3NaXxl-HYN _3f2ZtYT7NH _1f_YBwo4nE']/span/span[2]")
    private List<WebElement> itemsCurrency;

    @FindBy(xpath = "//div[@class='_2UmyXf5e76']")
    private List<WebElement> itemsLikeButton;

    private List<Item> itemsOnPage;

    public CategoryPage(WebDriver driver) {
        super(driver);
        this.itemsOnPage = new ArrayList<>();
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Category page created");
    }

    @Override
    public CategoryPage openPage() {
        return this;
    }

    public String getCategoryTitle(){
        CustomWaits.waitForPageLoaded(driver);
        return pageTitle.getText();
    }

    public List<String> getSearchResultTitles(){
        CustomWaits.waitForPageLoaded(driver);
        return itemsTitle.stream().map(item -> item.getText())
                .collect(Collectors.toList());
    }

    public List<String> getCurrency(){
        CustomWaits.waitForPageLoaded(driver);
        return itemsCurrency.stream().map(i -> i.getText())
                .collect(Collectors.toList());
    }

    public CategoryPage createItemList(){
        for (int i = 0; i < itemsTitle.size(); i++) {
            this.itemsOnPage.add(new Item(itemsTitle.get(i).getText()));
        }
        return this;
    }

    public Item addToFavourites(int num){
        CustomWaits.waitForPageLoaded(driver);
        itemsLikeButton.get(num).click();
        CustomWaits.waitForPageLoaded(driver);
        Item item = itemsOnPage.get(num);
        item.setFavourite(true);
        logger.info("Added to favourites" + item);
        CustomWaits.waitForElementLocated(driver, "//div[@class='_3oDLePObQ1']");
        return itemsOnPage.get(num);
    }

    public ItemPage goToItem(int num){
        itemsTitle.get(num).click();

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        CustomWaits.waitForPageLoaded(driver);
        return new ItemPage(driver);
    }
}
