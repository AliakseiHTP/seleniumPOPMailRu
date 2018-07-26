package by.htp.steps;

import by.htp.pages.MailBoxPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import by.htp.driver.DriverSingleton;
import by.htp.pages.LoginPage;

public class Step {
	
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void login(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public void sentLetter(){
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.sendLetter();
	}

	public void checkSentLetter() {
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.checkSentLetter();
    }

    public void deleteLetter() throws InterruptedException {
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.deleteLetter();
    }
}
