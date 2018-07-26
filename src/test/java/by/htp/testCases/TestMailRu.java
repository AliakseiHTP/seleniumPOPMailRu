package by.htp.testCases;

import by.htp.steps.Step;
import static by.htp.utils.PropertyManager.getProperty;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestMailRu {
	private Step step;
	private static final String LOGIN = getProperty("login");
    private static final String PASSWORD = getProperty("password");
    
    @BeforeMethod(description = "Init browser")
	public void setUp() {
		step = new Step();
		step.initBrowser();
	}

	@Test
	public void loginMailRu() throws InterruptedException {
		step.login(LOGIN, PASSWORD);
	}

    @Test
    public void writeMessage() throws InterruptedException {
        step.sentLetter();
        step.checkSentLetter();
    }
	
	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		//step.closeDriver();
	}
}
