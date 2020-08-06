package POMs;

import core.Config;
import helpers.CalcUtil;
import helpers.SeleniumUtils;
import helpers.WildcardUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import services.Currency;

import java.util.List;
import java.util.Map;

public class CursMD extends AbstractPOM {

    private static final Logger log = LogManager.getLogger(CursMD.class);
    private static final String SITE_URL = Config.getString("exchange.url");

    @FindBy(xpath = "//a[@id='language-select']")
    public WebElement languageBtn;
    @FindBy(xpath = "//a[@id='language-select']/following::ul[1]/li/a")
    public List<WebElement> languagesSelect;
    @FindBy(xpath = "(//button[contains(@class,'dropdown')])[1]")
    public WebElement leftDropdownBtn;
    @FindBy(xpath = "(//button[contains(@class,'dropdown')])[2]")
    public WebElement rightDropdownBtn;
    @FindBy(xpath = "//input[@class ='chosen-search-input' and .//ancestor::div[contains(@class,'chosen-container-active')]]")
    public WebElement searchInput;
    @FindBy(xpath = "//ul[@class='chosen-results' and .//ancestor::div[contains(@class,'chosen-container-active')]]/li")
    public List<WebElement> currenciesList;
    @FindBy(xpath = "//li[@class='page-curs_valutar_banci']")
    public WebElement exchangeRateBtn;
    @FindBy(xpath = "//li[@class='page-curs_valutar/oficial']")
    public WebElement officialRateBtn;
    @FindBy(xpath = "(//div[@class= 'input-group']/input)[1]")
    public WebElement leftInput;
    @FindBy(xpath = "(//div[@class= 'input-group']/input)[2]")
    public WebElement rightInput;
    @FindBy(xpath = "(//span[contains(@class,'-currency')])[1]")
    public WebElement leftCode;
    @FindBy(xpath = "(//span[contains(@class,'-currency')])[2]")
    public WebElement rightCode;
    @FindBy(xpath = "//table[@id='tabelValute']")
    public WebElement officialRateTable;

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

    /**
     * @param left  - charcode or wildcard
     * @param right - charcode or wildcard
     */
    public void selectCurrencies(String left, String right) {
        selectCurrency(true, WildcardUtil.processCharCode(left));
        selectCurrency(false, WildcardUtil.processCharCode(right));
    }

    private void selectCurrency(boolean isLeft, String charCode) {
        openDropdown(isLeft);
        searchInput.sendKeys(charCode + " -");
        SeleniumUtils.sleep(1);
        SeleniumUtils.clickJS(driver, currenciesList.get(0));
        SeleniumUtils.sleep(1);
    }

    public void enterValue(boolean isLeft, String value) {
        (isLeft ? leftInput : rightInput).sendKeys(WildcardUtil.processDouble(value));
        SeleniumUtils.sleep(2);
    }

    public void openDropdown(boolean isLeft) {
        (isLeft ? leftDropdownBtn : rightDropdownBtn).click();
        SeleniumUtils.sleep(2);
    }

    public void validateCalculation(Map<String, Currency> bnmCurrencies) {
        new CalcUtil(bnmCurrencies).validateCurrency(leftCode.getText(), leftInput, rightCode.getText(), rightInput);
    }

    public void alert(String alertMsg) {
        ((JavascriptExecutor) driver).executeScript("alert(\"" + alertMsg + "\")");
        SeleniumUtils.sleep(3);
        try {
            driver.switchTo().alert().accept();
        } catch (Throwable ignored) {}
    }
}
