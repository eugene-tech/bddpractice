package stepDefinitions;

import POMs.CursMD;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import helpers.SeleniumUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class OfficialExchangeRateTest {
    private static CursMD cursMD;

    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = new CursMD(SeleniumUtils.getInstance().getDriver());
    }

    @Then("^Open Exchange rates page$")
    public void openExchangeRatesPage() {
        cursMD.getExchangeRatesPage().openThisPage();
    }

    @Then("^Click on official exchange rate menu option$")
    public void clickOnOfficialExchangeRateMenuOption() {
        cursMD.getExchangeRatesPage().getOfficialExchangeRate().openThisPage();
    }

    @Then("^Check if official exchange rate was translated correctly based on language '(.*)'$")
    public void checkIfOfficialExchangeRateWasTranslatedCorrectlyBasedOnLanguageLang(String lang) throws JAXBException, IOException {
        cursMD.getExchangeRatesPage().getOfficialExchangeRate().isExchangeRateCorrect(lang);
    }


}
