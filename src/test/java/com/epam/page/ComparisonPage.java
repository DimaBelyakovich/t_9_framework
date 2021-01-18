package com.epam.page;

import com.epam.model.Item;
import com.epam.waits.CustomWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ComparisonPage extends MainPage{

    @FindBy(xpath = "//a[@class='_27nuSZ19h7 PzFNvA3yUm cia-cs']")
    private List<WebElement> itemsTitle;

    private List<Item> itemsOnPage;

    public ComparisonPage(WebDriver driver) {
        super(driver);
        this.itemsOnPage = new ArrayList<>();
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Comparison page created");
    }

    public ComparisonPage createItemsList(){
        for (int i = 0; i < itemsTitle.size(); i++) {
            Item item = new Item(itemsTitle.get(i).getText());
            item.setCompared(true);
            this.itemsOnPage.add(item);
        }
        return this;
    }

    public List<Item> getItems(){
        return this.itemsOnPage;
    }
}
