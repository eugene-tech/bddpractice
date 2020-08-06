package stepDefinitions;

import POMs.CursMD;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.*;
import helpers.DataUtil;
import helpers.Lang;
import helpers.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.BNMService;
import services.Currency;
import services.UICurrencyProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CursStepDefinitions {

    public static WebDriver driver;
    private CursMD cursMD;
    private static final String OPTION_FORMAT = "%s - %s";

    @Before
    public void loadDriver() {
        driver.manage().deleteAllCookies();
    }

    @After
    public void closeDriver(Scenario sc) {
        cursMD.alert("Test " + sc.getStatus().toUpperCase() + ", driver will be closed in a few seconds.....");
        SeleniumUtils.sleep(2);
    }

    @Допустим("^главная страница открыта$")
    public void openExchange() {
        cursMD = CursMD.init(driver);
    }

    @Если("^пользователь меняет язык на '(.*)'$")
    public void changeLanguage(String language) {
        cursMD.changeLanguage(language);
    }

    @То("^меню отображается на выбранном языке и состоит из ссылок '(.*)'$")
    public void menuBarCheck(List<String> menuItems) {
        menuItems.forEach(menuItem ->
                assertTrue("Menu item <" + menuItem + "> was not displayed", cursMD.getMenuItem(menuItem).isDisplayed()));
    }

    @Ктомуже("^название страницы меняется на '(.*)'$")
    public void tabCheck(String tabName) {
        assertEquals(tabName, driver.getTitle());
    }

    @И("^отрывает окно выбора валют$")
    public void openCurrenciesSelect() {
        cursMD.openDropdown(true);
    }

    @Тогда("^окно выбора валют меняется на '(.*)' в соответствии с bnm$")
    public void currencySelectCheck(String language) {
        List<String> uiCurrencies = cursMD.currenciesList.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        BNMService.getCurrencyRate(DataUtil.TODAY, Lang.getLang(language))
                .forEach((charCode, bnmCurrency) -> {
                    String optionText = uiCurrencies.stream()
                            .filter(option -> option.startsWith(charCode))
                            .findFirst().orElseThrow(() -> new AssertionError("CharCode {" + charCode + "} was not found")).trim();
                    assertEquals(optionText, String.format(OPTION_FORMAT, charCode, bnmCurrency.getName()));
                });
    }

    @Ктомуже("^вводит поисковой запрос '(.*)'$")
    public void enterSearch(String searchingCriteria) {
        cursMD.searchInput.sendKeys(searchingCriteria);
        SeleniumUtils.sleep(2);
    }

    @Тогда("^отображаются только записи '(.*)', содержащие искомое слово$")
    public void searchingResultsCheck(List<String> searchingResults) {
        SeleniumUtils.sleep(1);
        List<String> uiSearchResults = new CursMD(driver).currenciesList.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        searchingResults.forEach(expectedResult ->
                assertTrue(expectedResult + " was not found", uiSearchResults.contains(expectedResult)));
        assertEquals("count of searching results", searchingResults.size(), uiSearchResults.size());
    }

    @Тогда("^появляется сообщение об ошибке '(.*)'$")
    public void errorMsgCheck(String errorMsg) {
        assertEquals("Error msg is displayed", 1, cursMD.currenciesList.size());
        assertEquals(errorMsg, cursMD.currenciesList.get(0).getText());
    }

    @И("^отрывает страницу с официальным курсом валют$")
    public void openOfficialExchangeRate() {
        cursMD.exchangeRateBtn.click();
        SeleniumUtils.sleep(2);
        cursMD.officialRateBtn.click();
        SeleniumUtils.sleep(2);
    }

    @Тогда("^курс валют меняется на '(.*)' в соответствии с bnm$")
    public void languageChangedFor(String language) {
        Map<String, Currency> bnmCurrencies = BNMService.getCurrencyRate(DataUtil.TODAY, Lang.getLang(language));
        UICurrencyProcessor.parseFromCurs(cursMD, DataUtil.TODAY).forEach((charCode, currency) ->
                assertEquals("Unexpected language of currency", bnmCurrencies.get(charCode).getName(), currency.getName()));
    }

    @Если("^пользователь выбирает необходимые валюты '(.*)', '(.*)'$")
    public void selectCurrencies(String left, String right) {
        cursMD.selectCurrencies(left, right);
    }

    @И("^вводит сумму '(.*)'$")
    public void enterValue(String value) {
        cursMD.enterValue(true, value);
    }

    @Тогда("^отображается сумма эквивалентная введенной для выбранной валюты$")
    public void validateCalculation() {
        cursMD.validateCalculation(BNMService.getCurrencyRate(DataUtil.TODAY, Lang.EN));
    }

    @Тогда("^таблица Курсы НБМ отображается$")
    public void tableIsDisplayed() {
        assertTrue("Table was not displayed", cursMD.officialRateTable.isDisplayed());
    }

    @Ктомуже("^показывается курс за последние три дня$")
    public void validateOfficialExchangeTable() {
        IntStream.range(0, 3)
                .mapToObj(DataUtil.TODAY::minusDays)
                .forEach(this::validateTable);
    }

    private void validateTable(LocalDate dateToCheck) {
        Map<String, Currency> bnmCurrencies = BNMService.getCurrencyRate(dateToCheck, Lang.RU);
        String logMsg = "Unexpected ";
        UICurrencyProcessor.parseFromCurs(cursMD, dateToCheck).forEach((charCode, currency) -> {
            Currency bnmCurrency = bnmCurrencies.get(charCode);
            assertEquals(logMsg + "charCode ", bnmCurrency.getCharCode(), currency.getCharCode());
            assertEquals(logMsg + "value ", bnmCurrency.getValue(), currency.getValue(), DataUtil.EPSILON);
            assertEquals(logMsg + "nominal ", bnmCurrency.getNominal(), currency.getNominal());
            assertEquals(logMsg + "name ", bnmCurrency.getName(), currency.getName());
        });
    }
}
