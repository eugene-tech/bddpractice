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
}
