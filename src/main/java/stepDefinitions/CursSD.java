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

    @Then("^menu translates to chosen language and consists of '(.*)'$")
    public void menuBarCheck(List<String> menuItems) {
        for (String menuItem : menuItems)
            validateMenuItem(menuItem);
    }

    @And("^tab name changes to '(.*)'$")
    public void tabCheck(String tabName) {
        assertEquals(tabName, driver.getTitle());
    }

    @After
    public void closeDriver(Scenario sc) {
        SeleniumUtils.getInstance().alert("Test " + sc.getStatus().toUpperCase() + ", driver will be closed in a few seconds.....");
    }
}
