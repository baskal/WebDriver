package com.gd.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dbaskal on 2/21/14.
 */
public class SendMsgPage extends MenuWithMsgFolders{

    private final WebDriver driver;

    @FindBy(css = "input.js-input.compose__labels__input")
    private WebElement recipientTextBox;

    @FindBy(name = "Subject")
    private WebElement subjectTextBox;

    @FindBy(css = "div.b-toolbar__btn > span.b-toolbar__btn__text")
    private WebElement sendMsgButton;

    @FindBy(xpath = "//div[@class='is-submit_empty_message_in']//button[@type=\"submit\"]" )
    private WebElement sendBlankMsgButton;

    public WebElement getRecipientTextBox() {
        return recipientTextBox;
    }

    public void setRecipientTextBox(WebElement recipientTextBox) {
        this.recipientTextBox = recipientTextBox;
    }

    public WebElement getSubjectTextBox() {
        return subjectTextBox;
    }

    public void setSubjectTextBox(WebElement subjectTextBox) {
        this.subjectTextBox = subjectTextBox;
    }

    public WebElement getSendMsgButton() {
        return sendMsgButton;
    }

    public void setSendMsgButton(WebElement sendMsgButton) {
        this.sendMsgButton = sendMsgButton;
    }

    public WebElement getSendBlankMsgButton() {
        return sendBlankMsgButton;
    }

    public void setSendBlankMsgButton(WebElement sendBlankMsgButton) {
        this.sendBlankMsgButton = sendBlankMsgButton;
    }

    public SendMsgPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
