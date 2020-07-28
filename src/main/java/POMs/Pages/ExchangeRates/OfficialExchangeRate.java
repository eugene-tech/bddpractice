package POMs.Pages.ExchangeRates;

import POMs.AbstractPOM;
import POMs.Page;
import dto.ValCurs;
import helpers.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import properties.LinksProperties;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OfficialExchangeRate extends AbstractPOM implements Page {
    private WebDriver driver;
    @FindBy(xpath = "//table[@id='tabelValute']/tbody/tr")
    public List<WebElement> officialListRateTableRows;

    @FindBy(xpath = "//span[@class='input-group date']//input[@class='form-control']")
    public WebElement currentDate;

    @FindBy(xpath = "//table[@id='tabelValute']/thead/tr/th[last()-1]")
    public WebElement dayBeforeCurrentDate;

    @FindBy(xpath = "//table[@id='tabelValute']/thead/tr/th[last()]")
    public WebElement dayBeforeYesterday;

    @FindBy(xpath = "//li[contains(@class,'page-curs_valutar/oficial')]/a")
    public WebElement officialExchangeRateMenuOption;

    @FindBy(xpath = "//h1[@class='iefix']")
    public WebElement officialExchangeRatePageHeader;

    public OfficialExchangeRate(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void isOfficialExchangeRateTabCorrectTranslated(String expected) {
        Assertions.assertThat(officialExchangeRateMenuOption.getText()).isEqualTo(expected);
    }

    public String getOfficialExchangeFromMenuText() {
        return officialExchangeRateMenuOption.getText();
    }

    public void isExchangeRateCorrect(String lang) throws JAXBException, IOException {
        ValCurs valCurs = (ValCurs) XMLParser.parseXML(
                FileManager.readFromFileBasedOnLang(lang),
                ValCurs.class);

        List<String> expectedResults = valCurs.getValute().stream()
                .collect(ArrayList::new, (list, item) -> {
                    if (item.getNominal() > 1) {
                        list.add(item.getCharCode() + " " + item.getNominal() + " " + item.getName());
                    } else {
                        list.add(item.getCharCode() + " " + item.getName());
                    }
                }, ArrayList::addAll);

        log.info("Checking Exchange Rate language...");
        log.info("Actual -> ");
        parseTableFromWebElementToCollection(SeleniumUtils.getWaiter(SeleniumUtils.getInstance().getDriver())
                        .until(ExpectedConditions.visibilityOfAllElements(officialListRateTableRows)),
                true).forEach(v -> System.out.print(v + " | "));
        log.info("\n");

        Collections.sort(expectedResults);
        log.info("Expected -> ");
        expectedResults.forEach(v -> System.out.print(v + " | "));
        log.info("\n");

        Assertions.assertThat(parseTableFromWebElementToCollection(officialListRateTableRows, true)).isEqualTo(expectedResults);
    }

    private List<String> parseTableFromWebElementToCollection(List<WebElement> webElements, boolean sorted) {

        List<String> temp = webElements.stream().collect(ArrayList::new,
                (list, item) -> list.add(item.findElement(By.xpath("./td[1]/span")).getText() + " " + item.findElement(By.xpath("./td" +
                        "[2]/a")).getText()),
                ArrayList::addAll);
        ;
        if (sorted) {
            Collections.sort(temp);
            return temp;
        } else {
            return temp;
        }
    }

    public void isExchangeResultsDisplayedCorrect() throws JAXBException, ParseException {

        String currentFormattedDate = ConversionManager.parseDateFromWebElement(SeleniumUtils.getWaiter(driver)
                .until(ExpectedConditions.visibilityOf(currentDate)));

        List<ValCurs> expectedValCurses = ValCursManager.getValCursByDates(Arrays.asList(currentFormattedDate,
                dayBeforeCurrentDate.getText(), dayBeforeYesterday.getText()),
                LinksProperties.bnm_en_official_exchange_link);

        officialListRateTableRows.stream().forEach(tableElement -> {

            String charCode = tableElement.findElement(By.xpath("./td[1]/span")).getText();

            log.info("<-- Start check exchange rate for <" + charCode + "> -->");

            String actualCurrentRate = tableElement.findElement(By.xpath("./td[3]")).getText();
            String actualRateBeforeCurrentRate = tableElement.findElement(By.xpath("./td[6]")).getText();
            String actualRateBeforeYesterdayRate = tableElement.findElement(By.xpath("./td[7]")).getText();

            log.info("Actual exchange rate for <" + charCode + "> " +
                    "at " + currentFormattedDate + " -> " + actualCurrentRate);
            log.info("Actual exchange rate for <" + charCode + "> " +
                    "at " + dayBeforeCurrentDate.getText() + " -> " + actualRateBeforeCurrentRate);
            log.info("Actual exchange rate for <" + charCode + "> " +
                    "at " + dayBeforeYesterday.getText() + " -> " + actualRateBeforeYesterdayRate);

            String expectedCurrentRate =
                   DoubleUtils.getDecimalWithDefaultFormat(ValCursManager.getValCursByDate(expectedValCurses, currentFormattedDate)
                            .getValueBaseOnCharCode(charCode));
            String expectedRateBeforeCurrentRate =
                    DoubleUtils.getDecimalWithDefaultFormat(ValCursManager.getValCursByDate(expectedValCurses, dayBeforeCurrentDate.getText())
                            .getValueBaseOnCharCode(charCode));
            String expectedRateBeforeYesterdayRate =
                    DoubleUtils.getDecimalWithDefaultFormat(ValCursManager.getValCursByDate(expectedValCurses, dayBeforeYesterday.getText())
                            .getValueBaseOnCharCode(charCode));

            log.info("Expected exchange rate for <" + charCode + "> " +
                    "at " + currentFormattedDate + " -> " + expectedCurrentRate);
            log.info("Expected exchange rate for <" + charCode + "> " +
                    "at " + dayBeforeCurrentDate.getText() + " -> " + expectedRateBeforeCurrentRate);
            log.info("Expected exchange rate for <" + charCode + "> " +
                    "at " + dayBeforeYesterday.getText() + " -> " + expectedRateBeforeYesterdayRate);

            Assertions.assertThat(actualCurrentRate).isEqualTo(expectedCurrentRate);
            Assertions.assertThat(actualRateBeforeCurrentRate).isEqualTo(expectedRateBeforeCurrentRate);
            Assertions.assertThat(actualRateBeforeYesterdayRate).isEqualTo(expectedRateBeforeYesterdayRate);
        });
    }

    @Override
    public String getPageHeader() {
        return officialExchangeRatePageHeader.getText();
    }

    @Override
    public void openThisPage() {
        log.info("Open this " + getOfficialExchangeFromMenuText() + " page");
        SeleniumUtils.getWaiter(SeleniumUtils.getInstance().getDriver())
                .until(ExpectedConditions.elementToBeClickable(officialExchangeRateMenuOption)).click();
    }

    @Override
    public void checkTitle(String expected) {
        Assertions.assertThat(driver.getTitle()).isEqualTo(expected);
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getMenuTitle() {
        return officialExchangeRateMenuOption.getText();
    }
}
