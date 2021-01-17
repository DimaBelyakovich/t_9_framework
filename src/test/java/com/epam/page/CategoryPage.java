package com.epam.page;

import com.epam.waits.CustomWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends MainPage {

    @FindBy(xpath = "//h1[@class='_3wPGpzKmmn']")
    private WebElement pageCategory;

    @FindBy(xpath = "//h3[@class='_3dCGE8Y9v3 cLo1fZHm2y']")
    private List<WebElement> itemTitle;

    @FindBy(xpath = "//div[@class='_3NaXxl-HYN _3f2ZtYT7NH _1f_YBwo4nE']/span/span[2]")
    private List<WebElement> itemCurrency;

    public CategoryPage(WebDriver driver) {
        super(driver);
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Category page created");
    }

    @Override
    public CategoryPage openPage() {
        return this;
    }

    public String getCategoryTitle(){
        CustomWaits.waitForPageLoaded(driver);
        return pageCategory.getText();
    }

    public List<String> getSearchResultTitles(){
        CustomWaits.waitForPageLoaded(driver);
        return itemTitle.stream().map(item -> item.getText())
                .collect(Collectors.toList());
    }

    public List<String> getCurrency(){
        CustomWaits.waitForPageLoaded(driver);
        return itemCurrency.stream().map(i -> i.getText())
                .collect(Collectors.toList());
    }
}
