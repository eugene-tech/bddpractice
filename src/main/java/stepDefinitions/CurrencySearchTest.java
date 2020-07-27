package stepDefinitions;

import POMs.CursMD;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.SeleniumUtils;

public class CurrencySearchTest {
    private CursMD cursMD;
    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = new CursMD(SeleniumUtils.getInstance().getDriver());
    }

    @When("^user open currency list$")
    public void userOpenCurrencyList() {
        cursMD.openCurrencyList();
    }

    @And("^send any '(.*)' in search input$")
    public void sendAnySymbolsInSearchInput(String symbols) {
        cursMD.sendAnySymbolsToSearchInput(symbols);
    }

    @Then("^check if output search results is expected$")
    public void checkIfSearchResultsContainInputSymbols() {
        cursMD.isResultsContainTheSymbols();
    }
}
