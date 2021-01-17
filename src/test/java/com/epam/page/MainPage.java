package com.epam.page;

import com.epam.waits.CustomWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage{
    private static final String MAIN_URL = "https://market.yandex.by/";

    @FindBy(xpath = "//div[@class='_3RDrvB5y7W _1Vd4y0E_v8']")
    protected WebElement profileButton;

    @FindBy(xpath = "//input[@id='header-search']")
    protected WebElement searchLineInput;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement searchButton;

    @FindBy(xpath = "//button[@class='MOYcCv2eIJ _3UND8GjCtL']")
    protected WebElement changeCityButton;

    @FindBy(xpath = "//button[@class='zsSJkfeAPw _16jABpOZ2- gjdzW5ajbI _3WgR56k47x']")
    protected WebElement catalogButton;

    public MainPage(WebDriver driver) {
        super(driver);
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Main page created");
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(MAIN_URL);
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public String getLoggedUserName(){
        CustomWaits.waitForElementClickable(driver, profileButton);
        profileButton.click();
        WebElement loggedUser = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_10BSdt90pf _3rYu_TSC-x']")));
        return loggedUser.getText();
    }

    public MainPage setupCity(String city){
        changeCityButton.click();
        CustomWaits.waitForPageLoaded(driver);
        WebElement cityInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='_1pVZ3jklLF']")));
        cityInput.sendKeys(city);
        WebElement hintCity = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='aSUR-uUgeo']")));
        hintCity.click();
        WebElement submitCity = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='zsSJkfeAPw _16jABpOZ2- _36y1jOUHs5 LS3-2-cZ2Z']")));
        submitCity.click();
        logger.info("City set upped");
        CustomWaits.waitForPageLoaded(driver);
        return new MainPage(driver);
    }

    public MainPage closePopUp(){
        CustomWaits.waitForElementLocated(driver,"//button[@class='zsSJkfeAPw _16jABpOZ2- gjdzW5ajbI _1LRo65x8Fi']");
        WebElement closePopUpButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='zsSJkfeAPw _16jABpOZ2- gjdzW5ajbI _1LRo65x8Fi']")));
        closePopUpButton.click();
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public CategoryPage search(String category){
        CustomWaits.waitForElementClickable(driver, searchLineInput);
        searchLineInput.sendKeys(category);
        searchButton.click();
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Search done");
        return new CategoryPage(driver);
    }

    public CategoryPage goToCategory(String type, String category) {
        CustomWaits.waitForPageLoaded(driver);
        catalogButton.click();
        WebElement categoryType = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button/a[@class='_3Lwc_UVFq4']/span[text()=\""+type+"\"]")));
        Actions action = new Actions(driver);
        action.moveToElement(categoryType)
                .perform();
        WebElement categoryButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=\""+category+"\"]")));
        categoryButton.click();
        logger.info("Category opened");
        return new CategoryPage(driver);
    }
}
