package by.htp.testCases;

import by.htp.steps.Step;
import static by.htp.utils.PropertyManager.getProperty;

import org.testng.annotations.*;


public class TestMailRu {
	private Step step;
	private static final String LOGIN = getProperty("login");
    private static final String PASSWORD = getProperty("password");
    
    @BeforeTest(description = "Init browser")
	public void setUp() {
		step = new Step();
		step.initBrowser();
	}

	@Test(description = "Login to MailRu", priority = 1)
	public void loginMailRu() {
		step.login(LOGIN, PASSWORD);
	}

    @Test(description = "write letter to smb", priority = 2)
    public void writeLetter() {
        step.sentLetter();
        step.checkSentLetter();
    }

    @Test(description = "delete letter that was send", priority = 3)
    public void deleteLetter() throws InterruptedException {
        step.deleteLetter();
    }


	@AfterTest(description = "Stop Browser")
	public void stopBrowser() {
		step.closeDriver();
	}
}
