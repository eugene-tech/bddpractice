package core;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.SeleniumUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import stepDefinitions.CursStepDefinitions;

import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features", glue = "stepDefinitions")
public class TestRunner {

    @BeforeClass
    public static void methodBeforeClassIsLoaded() {
        CursStepDefinitions.driver = SeleniumUtils.setUpDriver();
    }

    @AfterClass
    public static void methodAfterExecutingIsDone() {
        Optional.ofNullable(CursStepDefinitions.driver).ifPresent(WebDriver::quit);
    }
}