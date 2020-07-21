package POMs.Pages;


import POMs.AbstractPOM;
import POMs.Page;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import properties.XPathProperties;

public class RatesEvolution extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = XPathProperties.rates_evolution_menu_option)
    public  WebElement rates_evolution_menu_option;
    @FindBy(xpath = XPathProperties.rates_evolution_page_header)
    public  WebElement rates_evolution_page_header;


    public RatesEvolution(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void openThisPage() {
        if(rates_evolution_menu_option.isDisplayed())
            rates_evolution_menu_option.click();
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
        return rates_evolution_menu_option.getText();
    }

    @Override
    public String getPageHeader(){
        return rates_evolution_page_header.getText();
    }


}
