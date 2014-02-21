package com.gd.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dbaskal on 2/21/14.
 */
public class SendMsgPage {

    private final WebDriver driver;

    @FindBy(css = "input.js-input.compose__labels__input")
    public WebElement recipientTextBox;

    @FindBy(id = "compose_223_ab_compose_subj")
    public WebElement subjectTextBox;

    @FindBy(css = "div.b-toolbar__btn > span.b-toolbar__btn__text")
    public WebElement sendMsgButton;

    @FindBy(xpath = "(//button[@type=\"submit\"])[22]")
    public WebElement sendBlankMsgButton;

    public SendMsgPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
