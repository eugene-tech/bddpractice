package POMs;

import helpers.Config;
import helpers.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CursMD extends AbstractPOM {

    private static final Logger log = LogManager.getLogger(CursMD.class);
    private static final String SITE_URL = Config.getString("homepage.url");

    @FindBy(xpath = "//a[@id='language-select']")
    public WebElement languageBtn;

    @FindBy(xpath = "//a[@id='language-select']/following::ul[1]/li/a")
    public List<WebElement> languagesSelect;

//    @FindBy(xpath = "//*[@id='menu-top-collapse']/ul/li[2]")
//    public List<WebElement> linkRatesBtn;


    public CursMD(WebDriver driver) {
        super(driver);
    }

    public static CursMD init(WebDriver driver) {
        log.info("Redirecting to " + SITE_URL);
        driver.get(SITE_URL);
        SeleniumUtils.sleep(1);
        return new CursMD(driver);
    }


    public void changeLanguage(String language) {
        if (languageBtn.getText().trim().equals(language)) {
            log.warn("Language <" + language + "> already set!");
        } else {
            log.info("Setting language to <" + language + ">");
            languageBtn.click();

            SeleniumUtils.sleep(2);
            SeleniumUtils.clickJS(driver, languagesSelect.stream()
                    .filter(x -> x.getText().contains(language))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Language <" + language + "> not found!")));
            SeleniumUtils.sleep(3);
        }
    }

    public WebElement getMenuItem(String text) {
        log.info("Searching element with text <" + text + ">");
        return driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[./span[text()='" + text + "']]"));
    }


    public WebElement getMenuHeader(String txt) {
        log.info("Searching element with text <" + txt + ">");
        return driver.findElement(By.xpath("//div[contains(@class, 'head-menu')]//a[text()='" + txt + "']"));
    }

    public WebElement getSlogan(String slogan) {
        log.info("Searching element with text <" + slogan + ">");
        return driver.findElement(By.xpath("//div[text()='" + slogan + "']"));
    }

    public WebElement getMainName(String mains) {
        log.info("Searching element with text <" + mains + ">");
        return driver.findElement(By.xpath("//*[@id='graficEvolutie']/h1[text() ='" + mains + "']"));
    }

    public WebElement getOperationCheck(String operations) {
        log.info("Searching element with text <" + operations + ">");
        return driver.findElement(By.xpath("//*[@id='content']/div/div[1]/ul//a[text()='" + operations + "']"));
    }

    public WebElement getConvertorName(String convertor) {
        log.info("Searching element with text <" + convertor + ">");
        return driver.findElement(By.xpath("//*[@id='convertor']/h1[text()='" + convertor + "']"));
    }

    public WebElement getHeaderForm(String headerform) {
        log.info("Searching element with text <" + headerform + ">");
        return driver.findElement(By.xpath("//*[@id='bankValute']/h1[text()='" + headerform + "']"));
    }

    public WebElement getBankFormName(String bankformname) {
        log.info("Searching element with text <" + bankformname + ">");
        return driver.findElement(By.xpath("//*[@id='bankBox']/h1[text()='" + bankformname + "']"));
    }

    public WebElement getExchangeOffices(String exchangeoffices) {
        log.info("Searching element with text <" + exchangeoffices + ">");
        return driver.findElement(By.xpath("//*[@id='bankBox']/h1[text()='" + exchangeoffices + "']"));
    }

    public WebElement getExchangeCurrencies(String currencies) {
        log.info("Searching element with text <" + currencies + ">");
        return driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/h1[text()='" + currencies + "']"));
    }




//    public WebElement getrelativeName(String relative) {
//        log.info("Searching element with text <" + relative + ">");
//        return driver.findElement(By.xpath("//*[@id='graficEvolutie']/div[1]/form/div[2]]"));
//    }

}