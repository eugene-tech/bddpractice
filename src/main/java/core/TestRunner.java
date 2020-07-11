package core;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.SeleniumUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import stepDefinitions.CursSD;

import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features", glue = "stepDefinitions"
//    , tags = "@testTag" // uncomment this line to run
)
public class TestRunner {

    @BeforeClass
    public static void methodBeforeClassIsLoaded() {
        CursSD.driver = SeleniumUtils.setUpDriver();
        //test1
    }

    @AfterClass
    public static void methodAfterExecutingIsDone() {
        Optional.ofNullable(CursSD.driver).ifPresent(WebDriver::quit);
    }
}