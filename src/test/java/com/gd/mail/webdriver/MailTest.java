package com.gd.mail.webdriver;

import com.gd.mail.pages.Dashboard;
import com.gd.mail.pages.MenuWithMsgFolders;
import com.gd.mail.pages.LoginPage;
import com.gd.mail.pages.SendMsgPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class MailTest {

    WebDriver webDriver = new FirefoxDriver();

    String myLogin = "selenium.test1";
    String myPassword = "selenium123";
    String recipientMsg = "baskal.darya@gmail.com";
    String subjectMsg = "test";

    @BeforeClass()
    public void loginToMail() {

        webDriver.get("https://mail.ru");

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.getLoginTextBox().sendKeys(myLogin);
        loginPage.getPasswordTextBox().sendKeys(myPassword);
        loginPage.getSubmitButton().click();

    }

    @AfterClass
    public void logout() {

        Dashboard dashboard = new Dashboard(webDriver);
        dashboard.getLogoutButton().click();

        webDriver.close();

    }

    @Test
    void testLogin() {

        Dashboard dashboard = new Dashboard(webDriver);
        assertTrue(dashboard.getLogoutButton().isDisplayed());

    }

    @Test
    public void testSendMsg() {

        webDriver.get("https://e.mail.ru/compose");

        SendMsgPage sendMsgPage = new SendMsgPage(webDriver);

        sendMsgPage.getRecipientTextBox().sendKeys(recipientMsg);
        sendMsgPage.getSubjectTextBox().sendKeys(subjectMsg);
        sendMsgPage.getSendMsgButton().click();
        sendMsgPage.getSendBlankMsgButton().click();

        expectLoading("отправлено", 10);

        webDriver.getTitle();
        assertEquals(webDriver.getTitle(), "Письмо отправлено - " + myLogin + "@mail.ru - Почта Mail.Ru");

    }

    @Test
    public void testInbox() {

        MenuWithMsgFolders msgFolders = new MenuWithMsgFolders(webDriver);
        msgFolders.getInbox().click();

        expectLoading("Входящие", 10);

        String title = webDriver.getTitle();
        assertTrue(title.endsWith(myLogin + "@mail.ru - Почта Mail.Ru"));

    }

    @Test
    public void testSent() {

        MenuWithMsgFolders msgFolders = new MenuWithMsgFolders(webDriver);
        msgFolders.getSent().click();

        expectLoading("Отправленные", 10);
        msgFolders.assertTitle(webDriver, "Отправленные", myLogin);

    }

    @Test
    public void testDrafts() {

        MenuWithMsgFolders msgFolders = new MenuWithMsgFolders(webDriver);
        msgFolders.getDrafts().click();

        expectLoading("Черновики", 10);
        msgFolders.assertTitle(webDriver, "Черновики", myLogin);

    }

    private void expectLoading(String title, long t) {
        new WebDriverWait(webDriver, t)
                .until(ExpectedConditions.titleContains(title));
    }

}