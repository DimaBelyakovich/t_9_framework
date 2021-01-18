package com.epam.page;

import com.epam.model.Item;
import com.epam.waits.CustomWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends MainPage{

    private By popUpButton = By.xpath("//div[@class='_3oDLePObQ1']");

    @FindBy(xpath = "//div[@class='_2UmyXf5e76']")
    private WebElement addToWishListButton;

    @FindBy(xpath = "//div[@class='_1CXljk9rtd']")
    private WebElement addToComparisonButton;

    @FindBy(xpath = "//h1[@class='_1BWd__A9LM _2OAACZwAjs undefined']")
    private WebElement itemTitle;

    private Item item;

    public ItemPage(WebDriver driver) {
        super(driver);
        this.item = new Item();
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Item page created");
    }

    public Item addToWishList(){
        CustomWaits.waitForPageLoaded(driver);
        addToWishListButton.click();
        this.item.setFavourite(true);
        CustomWaits.waitForElementLocated(driver, popUpButton);
        logger.info("Added to favourites" + this.item);
        return this.item;
    }

    public Item addToComparison(){
        CustomWaits.waitForPageLoaded(driver);
        addToComparisonButton.click();
        this.item.setCompared(true);
        CustomWaits.waitForElementLocated(driver, popUpButton);
        logger.info("Added to comparison" + this.item);
        return this.item;
    }

    public ItemPage creatItem(){
        this.item.setTitle(itemTitle.getText());
        return this;
    }

    public ItemPage closePopUp(){
        By closePopUpBy = By.xpath("//button[@class='_1mhpYwuyDz _2FIxngPL9Y JLgsgUmoge _1fzfn22Av_ _1t_-Z3RX29']");
        WebElement closePopUpButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(closePopUpBy));
        CustomWaits.waitForElementClickable(driver, closePopUpButton);
        closePopUpButton.click();
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public ComparisonPage goToComparisonPageFromPopUp(){
        WebElement goToComparison = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(popUpButton));
        goToComparison.click();
        CustomWaits.waitForPageLoaded(driver);
        return new ComparisonPage(driver);
    }
}
