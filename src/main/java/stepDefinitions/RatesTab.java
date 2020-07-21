//package stepDefinitions;
//
//import POMs.CursMD;
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import helpers.SeleniumUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//
//public class RatesTab {
//    private RatesTab ratesTab;
//    public static WebDriver driver;
//
//
//    @Before
//    public void deleteCookies() {
//        driver.manage().deleteAllCookies();
//    }
//
//
//    @After
//    public void closeDriver(Scenario sc) {
//        SeleniumUtils.getInstance().alert("Test " + sc.getStatus().toUpperCase() + ", driver will be closed in a few seconds.....");
//        SeleniumUtils.sleep(2);}
//
//
//}
