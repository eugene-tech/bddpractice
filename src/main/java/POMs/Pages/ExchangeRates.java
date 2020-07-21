package POMs.Pages;


import POMs.AbstractPOM;
import POMs.Page;
import dto.ValCurs;
import helpers.FileManager;
import helpers.SeleniumUtils;
import helpers.XMLParser;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import properties.XPathProperties;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExchangeRates extends AbstractPOM implements Page {
    private WebDriver driver;
    @FindBy(xpath = XPathProperties.exchangesRatesMenuOption)
    public WebElement exchangesRatesMenuOption;

    @FindBy(xpath = XPathProperties.officialListRate)
    public List<WebElement> officialListRate;

    @FindBy(xpath = XPathProperties.officialExchangeRateTab)
    public WebElement officialExchangeRateTab;

    @FindBy(xpath = XPathProperties.cashOperationsTab)
    public WebElement cashOperationsTab;

    public ExchangeRates(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void isOfficialExchangeRateTabCorrectTranslated(String expected){
        Assertions.assertThat(officialExchangeRateTab.getText()).isEqualTo(expected);
    }

    public String getOfficialExchangeRateTabText(){
        return officialExchangeRateTab.getText();
    }

    public void openExchangeRateTab(){
        if(officialExchangeRateTab.isDisplayed()){
            officialExchangeRateTab.click();
        }
    }

    public void isExchangeRateCorrect(String lang) throws JAXBException, IOException {
        ValCurs valCurs = (ValCurs)  XMLParser.parseXML(
                FileManager.readFromFileBasedOnLang(lang),
                ValCurs.class);

        List<String> expectedResults = valCurs.getValute().stream()
                .collect(ArrayList::new, (list, item)->list.add(item.getCharCode()+" "+item.getName()), ArrayList::addAll);

        System.out.println("Checking Exchange Rate language...");
        System.out.print("Actual -> "); SeleniumUtils.parseTextFromWebElementToCollection(officialListRate,"title",
                true).forEach(v-> System.out.print(v+" | "));
        System.out.println();

        Collections.sort(expectedResults);
        System.out.print("Expected -> "); expectedResults.forEach(v-> System.out.print(v+" | "));
        System.out.println();


        Assertions.assertThat(SeleniumUtils.parseTextFromWebElementToCollection(officialListRate,"title",true)).isEqualTo(expectedResults);
    }

    @Override
    public void openThisPage() {
        if(exchangesRatesMenuOption.isDisplayed())
            exchangesRatesMenuOption.click();
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
        return exchangesRatesMenuOption.getText();
    }

    @Override
    public String getPageHeader() {
        return officialExchangeRateTab.getText(); //xxxxxxxxx
    }
}
