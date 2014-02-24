package com.gd.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dbaskal on 2/21/14.
 */
public class MenuWithMsgFolders extends Dashboard{

    private final WebDriver driver;

    @FindBy(partialLinkText = "Входящие")
    protected WebElement inbox;

    @FindBy(linkText = "Отправленные")
    protected WebElement sent;

    @FindBy(linkText = "Черновики")
    protected WebElement drafts;

    public WebElement getInbox() {
        return inbox;
    }

    public void setInbox(WebElement inbox) {
        this.inbox = inbox;
    }

    public WebElement getSent() {
        return sent;
    }

    public void setSent(WebElement sent) {
        this.sent = sent;
    }

    public WebElement getDrafts() {
        return drafts;
    }

    public void setDrafts(WebElement drafts) {
        this.drafts = drafts;
    }

    public MenuWithMsgFolders(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
