package POMs;

import POMs.Pages.Banks;
import POMs.Pages.Currencies;
import POMs.Pages.ExchangeOffices;
import POMs.Pages.ExchangeRates.ExchangeRates;
import POMs.Pages.RatesEvolution;
import dto.ValCurs;
import helpers.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import properties.LinksProperties;
import sun.reflect.generics.tree.Wildcard;

import javax.swing.text.html.HTML;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CursMD extends AbstractPOM {
    private String currentCode;
    private String currentSum;
    private static final Logger log = LogManager.getLogger(CursMD.class);
    private static final String SITE_URL = Config.getString("homepage.url");

    @FindBy(xpath = "//a[@id='language-select']")
    private WebElement languageBtn;
    @FindBy(xpath = "//a[@id='language-select']/following::ul[1]/li/a")
    private List<WebElement> languagesSelect;
    @FindBy(xpath = "//button[@class='btn btn-dropdown dropdown-toggle']")
    private WebElement currencyViBtn;
    @FindBy(xpath = "//div[@class='col-sm-6 conversion-param']//div[@class='chosen-drop']/ul/li")
    private List<WebElement> currencyList;
    @FindBy(xpath = "//div[contains(@class,'conversion-param')]")
    private WebElement blockForExchangeFROM;
    @FindBy(xpath = "//div[contains(@class,'ref-suggestions')]")
    private WebElement blockForExchangeTO;
    @FindBy(xpath = "//div[@class='input-group date param-lbl']//input")
    private WebElement currentDate;
    @FindBy(xpath = "//input[@class='chosen-search-input']")
    private WebElement currencyMainPageSearchInput;

    //other pages
    private List<Page> allPages;
    private ExchangeRates exchangeRatesPage;
    private RatesEvolution ratesEvolutionPage;
    private Banks banks;
    private Currencies currencies;
    private ExchangeOffices exchangeOffices;

    public CursMD(WebDriver driver) {
        super(driver);
        exchangeRatesPage = new ExchangeRates(driver);
        ratesEvolutionPage = new RatesEvolution(driver);
        banks = new Banks(driver);
        currencies = new Currencies(driver);
        exchangeOffices = new ExchangeOffices(driver);
    }

    public ExchangeRates getExchangeRatesPage() {
        return exchangeRatesPage;
    }

    public RatesEvolution getRatesEvolutionPage() {
        return ratesEvolutionPage;
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

    public void openCurrencyList() {
        log.info("Open currency list");
        SeleniumUtils.sleep(1);
        SeleniumUtils.getWaiter(SeleniumUtils.getInstance().getDriver())
                .until(ExpectedConditions.elementToBeClickable(currencyViBtn)).click();
        SeleniumUtils.sleep(2);
    }

    public void isCurrencyListCorrect(String lang) throws JAXBException, IOException {
        ValCurs valCurs = (ValCurs) XMLParser.parseXML(
                FileManager.readFromFileBasedOnLang(lang),
                ValCurs.class);

        List<String> expectedResults = valCurs.getValute().stream()
                .collect(ArrayList::new, (list, item) -> list.add(item.getCharCode() + " - " + item.getName()), ArrayList::addAll);

        log.info("Checking main page currency list language...");
        log.info("Actual -> ");
        SeleniumUtils.parseTextFromWebElementToCollection(currencyList, true).forEach(v -> System.out.print(v + " | "));
        log.info("\n");

        Collections.sort(expectedResults);
        log.info("Expected -> ");
        expectedResults.forEach(v -> System.out.print(v + " | "));
        log.info("\n");

        Assertions.assertThat(SeleniumUtils.parseTextFromWebElementToCollection(currencyList, true)).isEqualTo(expectedResults);
    }

    public List<Page> getAllPages() {
        List<Page> allPages = new ArrayList<>();
        allPages.add(exchangeRatesPage);
        allPages.add(ratesEvolutionPage);
        allPages.add(banks);
        allPages.add(currencies);
        allPages.add(exchangeOffices);
        return allPages;
    }

    public void checkAllPagesLanguage(List<String> menuExpected, List<String> pageTabHeader, List<String> pageTitle) {
        getAllPages().forEach(pages -> {
            if (!(pages instanceof ExchangeRates)) {
                log.info("Clicked on this menu option -> " + pages.getMenuTitle());
                pages.openThisPage();
                Assertions.assertThat(pageTitle).contains(pages.getTitle().trim());
                Assertions.assertThat(pageTabHeader).contains(pages.getPageHeader().trim());
                Assertions.assertThat(menuExpected).contains(pages.getMenuTitle().trim());
                log.info("Page tab header -> " + pages.getPageHeader() + " | Menu name -> " + pages.getMenuTitle() +
                        " and PAGE TITLE  -> " + pages.getTitle() + " was successfully translated");
            }
        });
    }

    public WebElement getInputBlockForExchangeFROM() {
        return blockForExchangeFROM.findElement(By.xpath(".//div[@class='input-group']/input"));
    }

    public WebElement getInputBlockForExchangeTO() {
        return blockForExchangeTO.findElement(By.xpath(".//div[@class='input-group']/input"));
    }

    public List<WebElement> getCharCodesFromBlockForExchangeFROM() {
        return blockForExchangeFROM.findElements(By.xpath(".//div[@class='btn-group']/button"));
    }

    public List<WebElement> getCharCodesFromBlockForExchangeTO() {
        return blockForExchangeTO.findElements(By.xpath(".//div[@class='btn-group']/button"));
    }

    public void sendRandomSumForExchange(String sum) {
        currentSum = WildcardUtil.processDouble(sum);
        getInputBlockForExchangeFROM().sendKeys(currentSum);
    }

    public void selectCurrencyCode(String code1) {
        WebElement codeToSwitch = getCharCodesFromBlockForExchangeFROM()
                .stream()
                .filter(code -> code.getText().equals(code1))
                .findFirst().get();
        currentCode = codeToSwitch.getText();
        codeToSwitch.click();
    }

    public void checkSumExchange(List<String> code2) throws ParseException, JAXBException {

        ValCurs valCurs = ConversionManager.getValCursByDate(ConversionManager.parseDateFromWebElement(currentDate));

        getCharCodesFromBlockForExchangeTO().stream().forEach(code -> {
            if (code2.contains(code.getText())) {
                code.click();
                SeleniumUtils.sleep(1);
                //sum conversion check
                log.info("<--- " + currentSum + " " + currentCode + " to " + code.getText() + " --->");
                if (currentCode.equals("MDL")) {
                    ConversionManager.checkConversionFromMDL(getInputBlockForExchangeTO(),
                            valCurs,
                            currentSum,
                            code, log);
                } else if (code.getText().equals("MDL")) {
                    ConversionManager.checkConversionToMDL(getInputBlockForExchangeTO(),
                            valCurs,
                            currentSum,
                            currentCode,
                            log);
                } else {
                    ConversionManager.checkConversion(getInputBlockForExchangeTO(),
                            valCurs,
                            currentSum,
                            currentCode,
                            code, log);
                }
            }
        });
    }

    public void sendAnySymbolsToSearchInput(String symbols) {
        String symbol = WildcardUtil.processSymbols(symbols);
        log.info("Sending these symbols -> " + symbol);
        SeleniumUtils.getWaiter(driver).until(ExpectedConditions.visibilityOf(currencyMainPageSearchInput)).sendKeys(symbol);
    }

    public void isResultsContainTheSymbols() {
        String symbols = SeleniumUtils.getWaiter(driver)
                .until(ExpectedConditions.visibilityOf(currencyMainPageSearchInput)).getAttribute("value");
        log.info("Checking if search results contain these symbols -> " + symbols);

        currencyList.stream().forEach(webElement -> {
            log.info("Expected that -> " + webElement.getText() + " | contains these symbols -> " + symbols);
            Assert.assertTrue(StringUtils.containsIgnoreCase(webElement.getText(), symbols));
        });
    }

    public void isResultsInvalid() {
        String symbols = SeleniumUtils.getWaiter(driver)
                .until(ExpectedConditions.visibilityOf(currencyMainPageSearchInput)).getAttribute("value");
        log.info("Checking if search is invalid by this pattern “No results match <Search symbol>”");

        currencyList.stream().forEach(webElement -> {
            log.info("Expected search results | No results match " + symbols);
            log.info("Actual search results | " + webElement.getText());
            Assertions.assertThat(webElement.getText()).isEqualTo("No results match " + symbols);
        });
    }

}
