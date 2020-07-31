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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import properties.GeneralProperties;
import properties.LinksProperties;

import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//#REQ-600
@Listeners(TestResultsListener.class)
public class PageHeaderMenuTest {

    @DataProvider(name = "test-data")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"Про нас", "Получить курс", "Контакты"}
        };
    }

    @BeforeTest
    public void initSteps() throws Exception {
        LinksProperties.readProperty();
        GeneralProperties.readProperty();
        SeleniumUtils.setUpDriver().manage().deleteAllCookies();
    }

    @Test(dataProvider = "test-data")
    public void pageHeaderMenuTest(String menuHeader1, String menuHeader2, String menuHeader3) {
        CursMD cursMD = CursMD.init(SeleniumUtils.getInstance().getDriver());
        cursMD.checkAllPagesHeaderMenu(menuHeader1, menuHeader2, menuHeader3);
    }
}
