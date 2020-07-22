package stepDefinitions;

import POMs.CursMD;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.SeleniumUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class CurrencyFormatTest {
    private static CursMD cursMD;

    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
    }

    @Given("^Main page is opened$")
    public void mainPageIsOpened() {
        cursMD = CursMD.init(SeleniumUtils.getInstance().getDriver());
    }

    @When("^Page language was changed on '(.*)'$")
    public void pageLanguageWasChangedOnLang(String language) {
        cursMD.changeLanguage(language);
    }


    @Then("^Open currency list$")
    public void openCurrencyList() {
        cursMD.openCurrencyList();
    }

    @Then("^Check if currency list was translated correctly based on language '(.+)'$")
    public void checkIfCurrencyListWasTranslatedCorrectly(String lang) throws JAXBException, IOException {
        cursMD.isCurrencyListCorrect(lang);
    }


}
