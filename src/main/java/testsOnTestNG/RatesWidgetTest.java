package testsOnTestNG;

import POMs.CursMD;
import helpers.SeleniumUtils;
import helpers.WildcardUtil;
import listeners.TestResultsListener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import properties.GeneralProperties;
import properties.LinksProperties;

//#REQ-601
@Listeners(TestResultsListener.class)
public class RatesWidgetTest {

    @DataProvider(name = "test-data")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"Curs.md - Получить курс","${randWidth}","160","300"}
        };
    }

    @BeforeTest
    public void initSteps() throws Exception {
        LinksProperties.readProperty();
        GeneralProperties.readProperty();
        SeleniumUtils.setUpDriver().manage().deleteAllCookies();
    }

    @Test(dataProvider = "test-data")
    public void ratesWidgetTest(String pageTab,String randomWidth,final String MIN_RANGE,final String MAX_RANGE){
        String randWidth = WildcardUtil.processInteger(randomWidth);
        CursMD cursMD = CursMD.init(SeleniumUtils.getInstance().getDriver());
        cursMD.getRatesWidgetPage().openThisPage();
        cursMD.getRatesWidgetPage().checkTitle(pageTab);
        cursMD.getRatesWidgetPage().changeWidgetWidth(randWidth);
        cursMD.getRatesWidgetPage().checkWidgetWidth(randWidth,MAX_RANGE,MIN_RANGE);
    }
}
