package by.htp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{
	private final Logger logger = LogManager.getRootLogger();
	private static final String BASE_URL = "https://e.mail.ru/login";
	private static final String LOGIN_INPUT = "//input[@name='Login']";
	private static final String SUBMIT_BTN = "//button[@type='submit']";
    private static final String PASSWORD_INPUT = "//input[@name='Password']";
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		logger.info("Login page opened");
	}
	
	public void login(String username, String password) throws InterruptedException{
		WebElement loginTbx = driver.findElement(By.xpath(LOGIN_INPUT));
        loginTbx.sendKeys(username);
        WebElement nextBtn = driver.findElement(By.xpath(SUBMIT_BTN));
        nextBtn.click();
        WebElement passwordTbx = driver.findElement(By.xpath(PASSWORD_INPUT));
        passwordTbx.sendKeys(password);
        //nextBtn = driver.findElement(By.xpath(SUBMIT_BTN));
        nextBtn.click();
		logger.info("Login performed");
		Thread.sleep(5000);
	}

}
