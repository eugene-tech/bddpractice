package stepDefinitions;

import POMs.CursMD;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import helpers.SeleniumUtils;
import org.assertj.core.api.Assertions;

import java.util.List;

public class TabsLanguageTest {
    private static CursMD cursMD;

    @Before
    public void clearCookie() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = new CursMD(SeleniumUtils.getInstance().getDriver());
    }

    @Then("^We should click on '(.*)' option and check if '(.*)' and '(.*)' was translated correctly$")
    public void weShouldClickOnMenuOptionAndCheckIfPageTabHeaderAndTabPageWasTranslatedCorrectly(List<String> menuExpected,List<String> pageTabHeader,List<String> pageTitle) {
       cursMD.checkAllPagesLanguage(menuExpected,pageTabHeader,pageTitle);
    }
}
