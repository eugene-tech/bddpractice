package POMs.Pages;


import POMs.AbstractPOM;
import POMs.Page;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExchangeOffices extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class,'page-lista_csv')]/a")
    private WebElement exchangeOfficiesMenuOption;
    @FindBy(xpath = "//div[@id='bankBox']/h1")
    private  WebElement exchangeOfficiesPageHeader;


    public ExchangeOffices(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void openThisPage() {
        if(exchangeOfficiesMenuOption.isDisplayed())
            exchangeOfficiesMenuOption.click();
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
    public String getMenuTitle(){
        return exchangeOfficiesMenuOption.getText();
    }

    @Override
    public String getPageHeader(){
        return exchangeOfficiesPageHeader.getText();
    }
}
