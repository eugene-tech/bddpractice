package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumUtils {

    private static final Logger log = LogManager.getLogger(SeleniumUtils.class);

    public static void sleep(long seconds) {
        log.info("Sleeping - " + seconds + " sec");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException ignored) {}
    }

    public static WebDriver setUpDriver() {
        WebDriver driver = new ChromeDriver();
        log.info("Driver setup");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void clickJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
