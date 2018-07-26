package by.htp.pages;

import static java.lang.Math.random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPage {
	WebDriver driver;

	public abstract void openPage();

	AbstractPage(WebDriver driver){
		this.driver = driver;
	}
    private static final Logger logger = LogManager.getRootLogger();

	static boolean isPresentXpath(WebDriver driver, String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public static int set_iRandom_number(int iFrom, int iTo) {
        return iFrom + (int) (random() * iTo);
    }

    public void moveMouseOn(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void needSleep(int iTime) throws InterruptedException {
        Thread.sleep(iTime);
        logger.info(String.format("waiting %d millis", iTime));
    }

    public static void compareLabelText(String s1, String s2, WebDriver driver) throws InterruptedException{
        WebElement textOnSiteLabel = driver.findElement(By.xpath(s1));
        needSleep(1000);
        String sTextOnSite = textOnSiteLabel.getText();
        System.out.println(String.format("compares the text - '%s' (on site) & '%s' (reference)", sTextOnSite,s2));
        if(sTextOnSite.equals(s2)) {
            logger.info("comparing texts... - texts are coincided");
        } else logger.info("comparing texts... - texts are not coincide");
    }
}
