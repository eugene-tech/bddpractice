package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.GeneralProperties;

import java.util.*;
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
        System.setProperty("webdriver.chrome.driver",
                Objects.requireNonNull(SeleniumUtils.class.getClassLoader().getResource(GeneralProperties.chrome_driver)).getPath());
        WebDriver driver = new ChromeDriver();

        log.info("Driver setup");
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(GeneralProperties.PageLoadTimeOut),
                TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(GeneralProperties.ImplicitlyWait),
                TimeUnit.SECONDS);
        driver.manage().window().maximize();
        if (seleniumUtils == null)
            seleniumUtils = new SeleniumUtils(driver);
        return driver;
    }

    public static void clickJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static List<String> parseTextFromWebElementToCollection(List<WebElement> webElements, boolean sorted){
        List<String> temp = new ArrayList<>();
        for(WebElement element : webElements){
            temp.add(element.getText());
        }
        //List<String> temp = webElements.stream().collect(ArrayList::new, (list, item) -> list.add(item.getText()), ArrayList::addAll);
        //System.out.println("---------------------------");
        //temp.forEach(v-> System.out.println(v+" AFTER COPY |"));
        if(sorted){
            Collections.sort(temp);
            return temp;
        }else {
            return temp;
        }
    }

    public static List<String> parseTextFromWebElementToCollection(List<WebElement> webElements,String attributeName,
                                                                 boolean sorted){
        List<String> temp = webElements.stream().collect(ArrayList::new, (list, item) -> list.add(item.getText()+" "+item.getAttribute(attributeName)), ArrayList::addAll);;
        if(sorted){
            Collections.sort(temp);
            return temp;
        }else {
            return temp;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWaiter(WebDriver driver){
        return new WebDriverWait(driver,30);
    }

}
