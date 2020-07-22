package POMs.Pages;


import POMs.AbstractPOM;
import POMs.Page;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Currencies extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class,'page-lista_valute')]/a")
    public WebElement currenciesMenuOption;
    @FindBy(xpath = "//div[@id='content']/div[@class='container']/div/div[1]/h1")
    public  WebElement currenciesPageHeader;


    public Currencies(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void openThisPage() {
        if(currenciesMenuOption.isDisplayed())
            currenciesMenuOption.click();
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
        return currenciesMenuOption.getText();
    }

    @Override
    public String getPageHeader(){
        return currenciesPageHeader.getText();
    }
}
