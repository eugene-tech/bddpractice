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
import properties.XPathProperties;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExchangeRates extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class,'page-curs_valutar_banci')]")
    private WebElement exchangesRatesMenuOption;
    @FindBy(xpath ="//ul[@class='nav nav-pills menu-second-lvl']/li[contains(@class,'active')]/a")
    private WebElement exchangeRatesCurrentPage;

    //@FindBy(xpath = "//li[contains(@class,'page-curs_valutar/cash')]/a")
    //public WebElement cashOperationsTab;

    private OfficialExchangeRate officialExchangeRate;

    public ExchangeRates(WebDriver driver) {
        super(driver);
        this.driver = driver;
        officialExchangeRate = new OfficialExchangeRate(driver);
    }

    public OfficialExchangeRate getOfficialExchangeRate() {
        return officialExchangeRate;
    }

    @Override
    public void openThisPage() {
        log.info("Open this "+getMenuTitle()+" page");
        SeleniumUtils.getWaiter(SeleniumUtils.getInstance().getDriver())
                .until(ExpectedConditions.elementToBeClickable(exchangesRatesMenuOption)).click();
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
        return exchangeRatesCurrentPage.getText(); //xxxxxxxxx
    }
}
