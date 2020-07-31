package stepDefinitions;

import POMs.CursMD;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import helpers.SeleniumUtils;

import javax.xml.bind.JAXBException;
import java.text.ParseException;

public class CorrectOfficialExchangeRate {
    private static CursMD cursMD;

    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = new CursMD(SeleniumUtils.getInstance().getDriver());
    }

    @Then("^Check if the official exchange rate displayed the correct course for the last three days based on bnm exchange rate$")
    public void checkIfOfficialExchangeRateWasDisplaysTheCorrectCourseForLastDaysBasedOnBnmExchangeRate() throws JAXBException, ParseException {
       cursMD.getExchangeRatesPage().getOfficialExchangeRate().isExchangeResultsDisplayedCorrect();
    }
}
