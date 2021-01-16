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
        return itemTitle.stream().map(item -> item.getText())
                .collect(Collectors.toList());
    }
}
