package core;

import POMs.CursMD;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.FileManager;
import helpers.HttpClient;
import helpers.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import properties.GeneralProperties;
import properties.LinksProperties;
import stepDefinitions.CursSD;

import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features", glue = "stepDefinitions"
//    , tags = "@testTag" // uncomment this line to run
)
public class TestRunner {
    private static final Logger log = LogManager.getLogger(TestRunner.class);
    public static void initSteps() throws Exception {
        log.info("Prestaging was started..");
        //data setup & props parsing
        LinksProperties.readProperty();
        GeneralProperties.readProperty();
        //get val curs from bnm and write to data file
        HttpClient.initHttpTimeOutConnection(Integer.parseInt(GeneralProperties.HttpClientSocketTimeout),
                Integer.parseInt(GeneralProperties.HttpClientConnectTimeout));
        FileManager.writeToFile(HttpClient.get(LinksProperties.bnm_ro_official_exchange_link+GeneralProperties.date),
                GeneralProperties.val_curs_data_file_location_ro);
        FileManager.writeToFile(HttpClient.get(LinksProperties.bnm_ru_official_exchange_link+GeneralProperties.date),
                GeneralProperties.val_curs_data_file_location_ru);
        FileManager.writeToFile(HttpClient.get(LinksProperties.bnm_en_official_exchange_link+GeneralProperties.date),
                GeneralProperties.val_curs_data_file_location_en);
        log.info("Prestaging was ended..");
    }
    @BeforeClass
    public static void methodBeforeClassIsLoaded() throws Exception {

        initSteps();

        CursSD.driver = SeleniumUtils.setUpDriver();
    }

    @AfterClass
    public static void methodAfterExecutingIsDone() {
        Optional.ofNullable(CursSD.driver).ifPresent(WebDriver::quit);
    }
}
