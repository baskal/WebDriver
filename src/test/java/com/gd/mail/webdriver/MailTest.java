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

    @DataProvider(name = "rs")
    private Object[][] getRS() {
        return new Object[][] {
                {"baskal.darya@gmail.com", "test"}
        };
    }

    @BeforeClass()
    public void loginToMail() {

        webDriver.get("https://mail.ru");

        LoginPage loginPage = new LoginPage(webDriver);

        UserData userData = new UserData();
        loginPage.getLoginTextBox().sendKeys(userData.getLogin());
        loginPage.getPasswordTextBox().sendKeys(userData.getPassword());
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

    @Test(dataProvider = "rs")
    public void testSendMsg(String recipient, String subject) {

        webDriver.get("https://e.mail.ru/compose");

        SendMsgPage sendMsgPage = new SendMsgPage(webDriver);

        sendMsgPage.getRecipientTextBox().sendKeys(recipient);
        sendMsgPage.getSubjectTextBox().sendKeys(subject);
        sendMsgPage.getSendMsgButton().click();
        sendMsgPage.getSendBlankMsgButton().click();

        expectLoading("отправлено", 10);

        UserData userData = new UserData();
        webDriver.getTitle();
        assertEquals(webDriver.getTitle(), "Письмо отправлено - " + userData.getLogin() + "@mail.ru - Почта Mail.Ru");

    }

    @Test
    public void testInbox() {

        MenuWithMsgFolders msgFolders = new MenuWithMsgFolders(webDriver);
        msgFolders.getInbox().click();

        expectLoading("Входящие", 10);

        UserData userData = new UserData();
        String title = webDriver.getTitle();
        assertTrue(title.endsWith(userData.getLogin() + "@mail.ru - Почта Mail.Ru"));

    }

    @Test
    public void testSent() {

        MenuWithMsgFolders msgFolders = new MenuWithMsgFolders(webDriver);
        msgFolders.getSent().click();

        UserData userData = new UserData();
        expectLoading("Отправленные", 10);
        msgFolders.assertTitle(webDriver, "Отправленные", userData.getLogin());

    }

    @Test
    public void testDrafts() {

        MenuWithMsgFolders msgFolders = new MenuWithMsgFolders(webDriver);
        msgFolders.getDrafts().click();

        UserData userData = new UserData();
        expectLoading("Черновики", 10);
        msgFolders.assertTitle(webDriver, "Черновики", userData.getLogin());

    }

    private void expectLoading(String title, long t) {
        new WebDriverWait(webDriver, t)
                .until(ExpectedConditions.titleContains(title));
    }

}