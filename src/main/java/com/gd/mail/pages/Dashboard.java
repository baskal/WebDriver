package com.gd.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

/**
 * Created by dbaskal on 2/24/14.
 */
public class Dashboard {

    private final WebDriver driver;

    @FindBy(id = "PH_logoutLink")
    protected WebElement logoutButton;

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(WebElement logoutButton) {
        this.logoutButton = logoutButton;
    }

    public Dashboard(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void assertTitle(WebDriver driver, String s, String login){
        assertEquals(driver.getTitle(), s + " - " + login + "@mail.ru - Почта Mail.Ru");

    }
}

