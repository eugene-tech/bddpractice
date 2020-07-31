package stepDefinitions;

import POMs.CursMD;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import helpers.SeleniumUtils;

public class CurrencyInvalidSearchTest {
    private CursMD cursMD;
    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = new CursMD(SeleniumUtils.getInstance().getDriver());
    }
    @Then("^check if output search results is invalid and matches with pattern -> No results match <Search symbol>$")
    public void checkIfOutputSearchResultsIsInvalidAndMatchesWithPattern(){
        cursMD.isResultsInvalid();
    }
}
