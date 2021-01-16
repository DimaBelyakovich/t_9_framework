package com.epam.page;

import com.epam.model.User;
import com.epam.waits.CustomWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage{
    private static final String LOGIN_PAGE_URL = "https://passport.yandex.ru/auth/add?origin=market_desktop_header&retpath=https%3A%2F%2Fmarket.yandex.ru%2F%3Flr%3D213";

    @FindBy(xpath = "//input[@data-t='field:input-login']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@data-t='field:input-passwd']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        CustomWaits.waitForPageLoaded(driver);
        logger.info("Login page created");
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(LOGIN_PAGE_URL);
        return this;
    }

    public MainPage sendUserData(User user){
        emailInput.sendKeys(user.getUsername());
        logger.info("Email sent");
        submitButton.click();
        CustomWaits.waitForElementClickable(driver, passwordInput);
        passwordInput.sendKeys(user.getPassword());
        logger.info("Password sent");
        submitButton.click();
        CustomWaits.waitForPageLoaded(driver);
        return new MainPage(driver);
    }
}
