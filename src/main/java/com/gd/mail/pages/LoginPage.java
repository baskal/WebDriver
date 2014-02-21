package com.gd.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dbaskal on 2/21/14.
 */
public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "mailbox__login")
    public WebElement loginTextBox;

    @FindBy(id = "mailbox__password")
    public  WebElement passwordTextBox;

    @FindBy(id = "mailbox__auth__button")
    public WebElement submitButton;

    @FindBy(id = "PH_logoutLink")
    public WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


}
