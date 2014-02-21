package com.gd.mail.webdriver;

import com.gd.mail.pages.InboxPage;
import com.gd.mail.pages.LoginPage;
import com.gd.mail.pages.SendMsgPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MailTest {

    WebDriver webDriver = new FirefoxDriver();
    String myLogin = "selenium.test1";
    String myPassword = "selenium123";
    String recipientMsg = "baskal.darya@gmail.com";
    String subjectMsg = "test";

    @BeforeClass
    public void loginToMail() {

        webDriver.get("https://mail.ru");

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.loginTextBox.sendKeys(myLogin);
        loginPage.passwordTextBox.sendKeys(myPassword);
        loginPage.submitButton.click();

    }

    @AfterClass
    public void logout() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.logoutButton.click();

        webDriver.close();

    }

    @Test
    void testLogin() {

        LoginPage loginPage = new LoginPage(webDriver);
        assertTrue(loginPage.logoutButton.isDisplayed());

    }

    @Test
    public void testSendMsg() {

        webDriver.get("https://e.mail.ru/compose");

        SendMsgPage sendMsgPage = new SendMsgPage(webDriver);

        sendMsgPage.recipientTextBox.sendKeys(recipientMsg);
        sendMsgPage.subjectTextBox.sendKeys(subjectMsg);
        sendMsgPage.sendMsgButton.click();
        sendMsgPage.sendBlankMsgButton.click();

        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.titleContains("отправлено"));

        webDriver.getTitle();
        assertEquals(webDriver.getTitle(), "Письмо отправлено - " + myLogin + "@mail.ru - Почта Mail.Ru");

    }

    @Test
    public void testInbox() {

        InboxPage inboxPage = new InboxPage(webDriver);
        inboxPage.inbox.click();

        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.titleContains("Входящие"));

        String title = webDriver.getTitle();
        assertTrue(title.endsWith(myLogin + "@mail.ru - Почта Mail.Ru"));

    }

    @Test
    public void testSent() {

        InboxPage inboxPage = new InboxPage(webDriver);
        inboxPage.sent.click();

        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.titleContains("Отправленные"));

        assertEquals(webDriver.getTitle(), "Отправленные - " + myLogin + "@mail.ru - Почта Mail.Ru");

    }

    @Test
    public void testDrafts() {

        InboxPage inboxPage = new InboxPage(webDriver);
        inboxPage.drafts.click();

        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.titleContains("Черновики"));

        assertEquals(webDriver.getTitle(), "Черновики - " + myLogin + "@mail.ru - Почта Mail.Ru");

    }

}