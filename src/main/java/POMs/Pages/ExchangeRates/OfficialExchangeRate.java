package POMs.Pages.ExchangeRates;

import POMs.AbstractPOM;
import POMs.Page;
import dto.ValCurs;
import helpers.FileManager;
import helpers.SeleniumUtils;
import helpers.XMLParser;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfficialExchangeRate extends AbstractPOM implements Page {
    private WebDriver driver;
    @FindBy(xpath = "//table[@id='tabelValute']/tbody/tr")
    public List<WebElement> officialListRateTableRows;

    @FindBy(xpath = "//li[contains(@class,'page-curs_valutar/oficial')]/a")
    public WebElement officialExchangeRateMenuOption;

    @FindBy(xpath = "//h1[@class='iefix']")
    public WebElement officialExchangeRatePageHeader;

    public OfficialExchangeRate(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void isOfficialExchangeRateTabCorrectTranslated(String expected){
        Assertions.assertThat(officialExchangeRateMenuOption.getText()).isEqualTo(expected);
    }

    public String getOfficialExchangeFromMenuText(){
        return officialExchangeRateMenuOption.getText();
    }


    public void isExchangeRateCorrect(String lang) throws JAXBException, IOException {
        ValCurs valCurs = (ValCurs)  XMLParser.parseXML(
                FileManager.readFromFileBasedOnLang(lang),
                ValCurs.class);

        List<String> expectedResults = valCurs.getValute().stream()
                .collect(ArrayList::new, (list, item)-> {
                    if (item.getNominal() > 1) {
                        list.add(item.getCharCode()+" "+item.getNominal()+" "+item.getName());
                    } else {
                        list.add(item.getCharCode()+" "+item.getName());
                    }
                }, ArrayList::addAll);

        log.info("Checking Exchange Rate language...");
        log.info("Actual -> "); parseTableFromWebElementToCollection(SeleniumUtils.getWaiter(SeleniumUtils.getInstance().getDriver())
                        .until(ExpectedConditions.visibilityOfAllElements(officialListRateTableRows)),
                true).forEach(v-> System.out.print(v+" | "));
        log.info("\n");

        Collections.sort(expectedResults);
        log.info("Expected -> "); expectedResults.forEach(v-> System.out.print(v+" | "));
        log.info("\n");


        Assertions.assertThat(parseTableFromWebElementToCollection(officialListRateTableRows,true)).isEqualTo(expectedResults);
    }

    private  List<String> parseTableFromWebElementToCollection(List<WebElement> webElements, boolean sorted){

        List<String> temp = webElements.stream().collect(ArrayList::new,
                (list, item) -> list.add(item.findElement(By.xpath("./td[1]/span")).getText()+" "+item.findElement(By.xpath("./td" +
                        "[2]/a")).getText()),
                ArrayList::addAll);;
        if(sorted){
            Collections.sort(temp);
            return temp;
        }else {
            return temp;
        }
    }

    @Override
    public String getPageHeader() {
        return officialExchangeRatePageHeader.getText();
    }

    @Override
    public void openThisPage() {
        log.info("Open this "+getOfficialExchangeFromMenuText()+" page");
        SeleniumUtils.getWaiter(SeleniumUtils.getInstance().getDriver())
                .until(ExpectedConditions.elementToBeClickable(officialExchangeRateMenuOption)).click();
    }

    @Override
    public void checkTitle(String expected) {
        Assertions.assertThat(driver.getTitle()).isEqualTo(expected);
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getMenuTitle() {
        return officialExchangeRateMenuOption.getText();
    }
}
