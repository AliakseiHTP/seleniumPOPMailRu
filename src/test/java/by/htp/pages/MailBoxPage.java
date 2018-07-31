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
    private static final String SEND_LETTER = "//div[@id='b-toolbar__left']//a[@data-name='compose']";
    private static final String EMAIL_TO = getProperty("email_to");
    private static final String EMAIL_TOPIC = getProperty("mail_topic");
    private static final String EMAIL_TEXT = getProperty("mail_text");
    private static final String I_FRAME = "//iframe[contains(@id,'composeEditor_ifr')]";
    private static final String SEND_TO = "//textarea[@data-original-name='To']";
    private static final String EMAIL_TOPIC_INPUT = "//input[@name='Subject']";
    private static final String EMAIL_TEXT_INPUT = "//body[@id='tinymce']";
    private static final String EMAIL_SEND = "(//div[@data-name='send']//span[@class='b-toolbar__btn__text'])[1]";
    private static final String SENT_LETTER = "//a[@href='/messages/sent/']";
    private static final String NEED_LETTER = "//a[@data-subject='%s']";
    private static final String DELETE_LETTER = "(//div[@data-mnemo='toolbar-letter']//div[@data-name='remove'])[1]";
    private static final String sTopic = (EMAIL_TOPIC + set_iRandom_number(1,1000));

    public MailBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    @Override
    public void openPage() {
        Assert.assertTrue(isPresentXpath(driver, SEND_LETTER));
        logger.info("MailBoxPage page opened");
    }

    public void sendLetter(){
        WebElement sendLetter = driver.findElement(By.xpath(SEND_LETTER));
        sendLetter.click();
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
        logger.info(String.format("Letter sent to %s",EMAIL_TO));
    }

    public void checkSentLetter() {
        WebElement sentLetter = driver.findElement(By.xpath(SENT_LETTER));
        sentLetter.click();
        if(isPresentXpath(driver, String.format(NEED_LETTER,sTopic))) {
            WebElement needMail = driver.findElement(By.xpath(String.format(NEED_LETTER, sTopic)));
            needMail.click();
            logger.info("The letter was sent");
        } else logger.info("The letter wasn't send");
    }

    public void deleteLetter() throws InterruptedException {
        WebElement deleteLetter = driver.findElement(By.xpath(DELETE_LETTER));
        deleteLetter.click();
        needSleep(2000);
        WebElement sentLetter = driver.findElement(By.xpath(SENT_LETTER));
        sentLetter.click();
        if(!isPresentXpath(driver, String.format(NEED_LETTER,sTopic))) {
            logger.info("The letter deleted");
        } else logger.info("The letter wasn't delete");
    }
}
