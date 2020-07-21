package stepDefinitions;

import POMs.CursMD;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CursSD {

    private CursMD cursMD;
    public static WebDriver driver;

    @Before
    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    @Given("^main page is opened$")
    public void openExchange() {
        cursMD = CursMD.init(driver);
    }



    @When("^user changes language to '(.*)'$")
    public void changeLanguage(String language) {
        cursMD.changeLanguage(language);
    }

    private void validateMenuItem(String menuItem) {
        assertTrue("Menu item <" + menuItem + "> was not displayed", cursMD.getMenuItem(menuItem).isDisplayed());
    }

    private void validateMenuHeader(String menuHeader) {
        assertTrue("Menu header <" + menuHeader + "> was not displayed",
                cursMD.getMenuHeader(menuHeader).isDisplayed());
    }

    private void validateOperationNames(String operationNames) {
        assertTrue("Operation names function<" + operationNames + "> was not displayed",
                cursMD.getOperationCheck(operationNames).isDisplayed());
    }


    @Then("^menu translates to chosen language and consists of '(.*)'$")
    public void menuBarCheck(List<String> menuItems) {
        for (String menuItem : menuItems)
            validateMenuItem(menuItem);
    }

    @And("^tab name changes to '(.*)'$")
    public void tabCheck(String tabName) {
        assertEquals(tabName, driver.getTitle());
    }

    @And("^header name changed to '(.*)'$")
    public void headerNameChangedToHeader(List<String> menuHeaders) {
        for (String menuHeader : menuHeaders)
            validateMenuHeader(menuHeader);
    }

    @And("^slogan changed to '(.*)'$")
    public void sloganChangedToSlogan(String sloganName) {
        assertTrue("Main names <" + sloganName + "> was not displayed",
                cursMD.getSlogan(sloganName).isDisplayed());
    }


  // req104

    @When("^user changes page to 'Rates evolution'$")
    public void userChangesPageToRatesEvolution() {
        driver.findElement(By.xpath("//*[@id='menu-top-collapse']/ul/li[2]")).click();
    }

    @Then("^page was changed to 'Rates evolution'$")
    public void pageWasChangedToRatesEvolution() {
        System.out.println("'Rates evolution' page was opened");
    }


    @And("^main info was translated to chosen language '(.*)'$")
    public void mainInfoWasTranslatedToChosenLanguageMains(String mainsName) {
        assertTrue("Main names <" + mainsName + "> was not displayed",
                cursMD.getMainName(mainsName).isDisplayed());

    }

//    @And("^relative translate '(.*)'$")
//    public void relativeTranslateRelative(String relativeName) {
//        assertTrue("relative Name  <" + relativeName + "> was not displayed",
//                cursMD.getrelativeName(relativeName).isDisplayed());
//    }
//    @And("^bank translate '<bank>'$")
//    public void bankTranslateBank() {
//    }

    @When("^user changes page to 'Convertor'$")
    public void userChangesPageToConvertor() {
        driver.findElement(By.xpath("//*[@id='menu-top-collapse']/ul/li[3]")).click();
    }

    @Then("^page was changed to 'Convertor'$")
    public void pageWasChangedToConvertor() {
        System.out.println("'Convertor' page was opened");
    }

    @And("^operation options were translated to chosen language '(.*)'$")
    public void operationOptionsWereTranslatedToChosenLanguageOperations(List <String> operationsValid) {
        for (String oprationVal : operationsValid)
            validateOperationNames(oprationVal);
    }

    @And("^name of convertor changes to chosen language '(.*)'$")
    public void nameOfConvertorChangesToChosenLanguageConvertors(String convertorName) {
        assertTrue("Convertor Name  <" + convertorName + "> was not displayed",
                cursMD.getConvertorName(convertorName).isDisplayed());
    }


    @When("^user changes page to 'Exchange rates'$")
    public void userChangesPageToExchangeRates() {
        driver.findElement(By.xpath("//*[@id='menu-top-collapse']/ul/li[4]")).click();
    }

    @Then("^page was changed to 'Exchange rates'$")
    public void pageWasChangedToExchangeRates() {
        System.out.println("'Exchange rates' page was opened");
    }

    @And("^header name of form was changed '(.*)'$")
    public void headerNameOfFormWasChangedHeaderform(String headerform) {
        assertTrue("headerform Name  <" + headerform + "> was not displayed",
                cursMD.getHeaderForm(headerform).isDisplayed());
    }

    @When("^user changes page to 'Bank'$")
    public void userChangesPageToBank() {
        driver.findElement(By.xpath("//*[@id='menu-top-collapse']/ul/li[5]")).click();
    }

    @Then("^page was changed to 'Bank'$")
    public void pageWasChangedToBank() {
        System.out.println("'Bank' page was opened");
    }

    @And("^name of form was translated to chosen language '(.*)'$")
    public void nameOfFormWasTranslatedToChosenLanguageBankformname(String bankformname) {
        assertTrue("Bankformname Name  <" + bankformname + "> was not displayed",
                cursMD.getBankFormName(bankformname).isDisplayed());
    }


    @When("^user changes page to 'Exchange Offices'$")
    public void userChangesPageToExchangeOffices() {
        driver.findElement(By.xpath("//*[@id='menu-top-collapse']/ul/li[6]")).click();
    }

    @Then("^page was changed to 'Exchange Offices'$")
    public void pageWasChangedToExchangeOffices() {
        System.out.println("'Exchange Offices' page was opened");
    }

    @And("^list of exchange was translated to chosen language '(.*)'$")
    public void listOfExchangeWasTranslatedToChosenLanguageExchangeoffices(String exchangeoffices) {
        assertTrue("Exchange Offices Name  <" + exchangeoffices + "> was not displayed",
                cursMD.getExchangeOffices(exchangeoffices).isDisplayed());
    }

    @When("^user changes page to 'Currencies'$")
    public void userChangesPageToCurrencies() {
        driver.findElement(By.xpath("//*[@id='menu-top-collapse']/ul/li[7]")).click();
    }

    @Then("^page was changed to 'Currencies'$")
    public void pageWasChangedToCurrencies() {
        System.out.println("'Currencies' page was opened");
    }

    @And("^list of the currencies was changed '(.*)'$")
    public void listOfTheCurrenciesWasChangedCurrencies(String currencies) {
        assertTrue("Exchange Offices Name  <" + currencies + "> was not displayed",
                cursMD.getExchangeCurrencies(currencies).isDisplayed());
    }

        @After
    public void closeDriver(Scenario sc) {
        SeleniumUtils.getInstance().alert("Test " + sc.getStatus().toUpperCase() + ", driver will be closed in a few seconds.....");
        SeleniumUtils.sleep(2);
    }


}
