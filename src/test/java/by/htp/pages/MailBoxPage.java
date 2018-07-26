package by.htp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static by.htp.utils.PropertyManager.getProperty;

public class MailBoxPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private static final String SEND_MESSAGE = "//div[@id='b-toolbar__left']//a[@data-name='compose']";
    private static final String EMAIL_TO = getProperty("email_to");
    private static final String EMAIL_TOPIC = getProperty("mail_topic");
    private static final String EMAIL_TEXT = getProperty("mail_text");
    private static final String I_FRAME = "//iframe[contains(@id,'composeEditor_ifr')]";
    private static final String SEND_TO = "//textarea[@data-original-name='To']";
    private static final String EMAIL_TOPIC_INPUT = "//input[@name='Subject']";
    private static final String EMAIL_TEXT_INPUT = "//body[@id='tinymce']";
    private static final String EMAIL_SEND = "(//div[@data-name='send']//span[@class='b-toolbar__btn__text'])[1]";
    private static final String SENT_MESSAGE = "//a[@href='/messages/sent/']";
    private static final String NEED_MESSAGE = "//a[@data-subject='%s']";
    private static final String DELETE_MESSAGE = "(//div[@data-mnemo='toolbar-letter']//div[@data-name='remove'])[1]";

    public MailBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    @Override
    public void openPage() {
        Assert.assertTrue(isPresentXpath(driver,SEND_MESSAGE));
        logger.info("MailBoxPage page opened");
    }

    public void writeMessage(){
        String sTopic = (EMAIL_TOPIC + set_iRandom_number(1,1000));

        WebElement sendMessage = driver.findElement(By.xpath(SEND_MESSAGE));
        sendMessage.click();
        WebElement sendTo = driver.findElement(By.xpath(SEND_TO));
        sendTo.sendKeys(EMAIL_TO);
        WebElement emailTopic = driver.findElement(By.xpath(EMAIL_TOPIC_INPUT));
        emailTopic.sendKeys(sTopic);
        WebElement iFrame = driver.findElement(By.xpath(I_FRAME));
        driver.switchTo().frame(iFrame);
        WebElement emailText = driver.findElement(By.xpath(EMAIL_TEXT_INPUT));
        emailText.sendKeys(EMAIL_TEXT);
        driver.switchTo().defaultContent();
        WebElement sendEmail = driver.findElement(By.xpath(EMAIL_SEND));
        sendEmail.click();
    }

}
