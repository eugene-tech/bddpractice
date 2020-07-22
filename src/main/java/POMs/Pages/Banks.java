package POMs.Pages;


import POMs.AbstractPOM;
import POMs.Page;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Banks extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class,'page-lista_banci')]/a")
    public WebElement banksMenuOption;
    @FindBy(xpath = "//div[@id='bankBox']/h1")
    public  WebElement banksPageHeader;


    public Banks(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void openThisPage() {
        if(banksMenuOption.isDisplayed())
            banksMenuOption.click();
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
        return banksMenuOption.getText();
    }

    @Override
    public String getPageHeader(){
        return banksPageHeader.getText();
    }
}
