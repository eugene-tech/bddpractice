package stepDefinitions;

import POMs.CursMD;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.SeleniumUtils;

import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.util.List;

public class ExchangeRatesTest {
    private static CursMD cursMD;
    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = new CursMD(SeleniumUtils.getInstance().getDriver());
    }

    @When("^user send '(.*)' for exchange$")
    public void userSendSumForExchange(String sum) {
           cursMD.sendRandomSumForExchange(sum);
    }

    @And("^user select currency code for exchange from '(.*)'$")
    public void userSelectCurrencyForExchangeFromCode(String code1) {
        cursMD.selectCurrencyCodeFROM(code1);
    }


    @Then("^user select currency code for exchange to '(.*)' and check if output sum was exchanged based on bnm exchange " +
            "rate$")
    public void userSelectCurrencyForExchangeToCharCodeAndCheckIfSumWasExchangedBasedOnBnmExchangeRate(List<String> code2) throws JAXBException, ParseException {
        cursMD.checkSumExchange(code2);
    }
}
