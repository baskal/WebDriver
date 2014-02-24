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
    private WebElement loginTextBox;

    @FindBy(id = "mailbox__password")
    private WebElement passwordTextBox;

    @FindBy(id = "mailbox__auth__button")
    private WebElement submitButton;

    public WebElement getLoginTextBox() {
        return loginTextBox;
    }

    public void setLoginTextBox(WebElement loginTextBox) {
        this.loginTextBox = loginTextBox;
    }

    public WebElement getPasswordTextBox() {
        return passwordTextBox;
    }

    public void setPasswordTextBox(WebElement passwordTextBox) {
        this.passwordTextBox = passwordTextBox;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
