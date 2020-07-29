package testsOnTestNG;

import POMs.CursMD;
import dto.ValCurs;
import helpers.ConversionManager;
import helpers.DateUtils;
import helpers.SeleniumUtils;
import helpers.ValCursManager;
import listeners.TestResultsListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import properties.GeneralProperties;
import properties.LinksProperties;

import javax.xml.bind.JAXBException;
import java.text.ParseException;

//test #REQ-202
//@Listeners(TestResultsListener.class)
public class ExchangeTestWithCalendar {
    private static final Logger log = LogManager.getLogger(ExchangeTestWithCalendar.class);
    private static String maxCurrentDate;

    @DataProvider(name ="test-data")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"MDL","USD"}
        };
    }

    @BeforeTest
    public void initSteps() throws Exception {
        LinksProperties.readProperty();
        GeneralProperties.readProperty();
        SeleniumUtils.setUpDriver().manage().deleteAllCookies();
        maxCurrentDate = ValCursManager.checkMaxCurrentDate();
    }


    @Test(dataProvider = "test-data")
    public void exchangeTestWithCalendar(String code1, String code2) throws ParseException, JAXBException {
        CursMD cursMD = CursMD.init(SeleniumUtils.getInstance().getDriver());
        log.info("<-----We have bnm data until "+maxCurrentDate+"----->");
        cursMD.selectCurrencyCodeFROM(code1);
        SeleniumUtils.sleep(1);
        cursMD.selectCurrencyCodeTO(code2);



        String currentDate = ConversionManager.parseDateFromWebElement(cursMD.getCurrentDate());
        ValCurs valCursCurrent = ConversionManager.getValCursByDate(currentDate);

        log.info("The select day before the current day "+currentDate+" from the calendar");

        SeleniumUtils.sleep(1);
        cursMD.getCurrentDate().sendKeys(DateUtils.getYesterdayDateString("dd"));
        SeleniumUtils.sleep(1);
        cursMD.selectCurrencyCodeTO(code2);

        ValCurs valCurs = ConversionManager.getValCursByDate(ConversionManager.parseDateFromWebElement(cursMD.getCurrentDate()));

        log.info("Beginning check Exchange from "+code1+" to "+code2+" at date "+cursMD.getCurrentDate().getAttribute(
                "value"));

        ConversionManager.checkConversionFromMDL(cursMD.getInputBlockForExchangeTO(),
                valCurs,
                cursMD.getInputBlockForExchangeFROM().getAttribute("value"),
                code2, log);

        log.info("Going back to the current date "+currentDate);

        SeleniumUtils.sleep(1);
        cursMD.getCurrentDate().sendKeys(DateUtils.changeDateFormat(currentDate,"dd.MM.yyyy","dd"));
        cursMD.selectCurrencyCodeTO(code2);

        log.info("Trying to select Date without data from bnm which should be higher than -> "+maxCurrentDate);

        SeleniumUtils.sleep(1);
        cursMD.getCurrentDate().sendKeys(DateUtils.getDateAfterDateString(1,maxCurrentDate,"dd.MM.yyyy","ddMM"));
        cursMD.selectCurrencyCodeTO(code2);
        log.info("Was selected this date "+DateUtils.getDateAfterDateString(1,maxCurrentDate,"dd.MM.yyyy","dd.MM.yyyy"));

        Assertions.assertThat(ConversionManager.parseDateFromWebElement(cursMD.getCurrentDate())).isEqualTo(currentDate);

        ConversionManager.checkConversionFromMDL(cursMD.getInputBlockForExchangeTO(),
                valCursCurrent,
                cursMD.getInputBlockForExchangeFROM().getAttribute("value"),
                code2, log);
        log.info("As expected current date "+cursMD.getCurrentDate().getAttribute("value")+" was not changed");


    }

}
