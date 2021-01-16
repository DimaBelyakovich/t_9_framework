package com.epam.page;

import com.epam.waits.CustomWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage{
    private static final String MAIN_URL = "https://market.yandex.by/";

    @FindBy(xpath = "//div[@class='_3RDrvB5y7W _1Vd4y0E_v8']")
    private WebElement profileButton;

    public MainPage(WebDriver driver) {
        super(driver);
        logger.info("Main page created");
        CustomWaits.waitForPageLoaded(driver);
    }

    @Override
    public AbstractPage openPage() {
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
}
