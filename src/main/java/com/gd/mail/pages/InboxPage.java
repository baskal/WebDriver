package com.gd.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dbaskal on 2/21/14.
 */
public class InboxPage {

    private final WebDriver driver;

    @FindBy(css = "span.b-nav__item__text")
    public WebElement inbox;

    @FindBy(xpath = "//*[@id='b-nav_folders']/div/a[2]/div/span")
    public WebElement sent;

    @FindBy(xpath = "//div[@id='b-nav_folders']/div/a[3]/div/span")
    public WebElement drafts;

    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
