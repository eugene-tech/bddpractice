package POMs.Pages;


import POMs.AbstractPOM;
import POMs.Page;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RatesEvolution extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class,'page-grafic_evolutii')]")
    public  WebElement ratesEvolutionMenuOption;
    @FindBy(xpath = "//div[@id='graficEvolutie']/h1")
    public  WebElement ratesEvolutionPageHeader;


    public RatesEvolution(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void openThisPage() {
        if(ratesEvolutionMenuOption.isDisplayed())
            ratesEvolutionMenuOption.click();
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
        return ratesEvolutionMenuOption.getText();
    }

    @Override
    public String getPageHeader(){
        return ratesEvolutionPageHeader.getText();
    }


}
