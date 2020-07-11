package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SeleniumUtils {

    private final WebDriver driver;
    private static SeleniumUtils seleniumUtils;
    private static final Logger log = LogManager.getLogger(SeleniumUtils.class);

    private SeleniumUtils(WebDriver driver) {this.driver = driver;}

    public void alert(String alertMsg) {
        ((JavascriptExecutor) driver).executeScript("alert(\"" + alertMsg + "\")");
        SeleniumUtils.sleep(3);
        try {
            driver.switchTo().alert().accept();
        } catch (Throwable ignored) {}
    }

    public static SeleniumUtils getInstance() {
        return Optional.ofNullable(seleniumUtils)
                .orElseThrow(() -> new IllegalStateException("Please make sure driver was set up!"));
    }

    public static void sleep(long seconds) {
        log.info("Sleeping - " + seconds + " sec");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException ignored) {}
    }

    public static WebDriver setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "F:/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        log.info("Driver setup");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        if (seleniumUtils == null)
            seleniumUtils = new SeleniumUtils(driver);
        return driver;
    }

    public static void clickJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
