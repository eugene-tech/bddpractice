package POMs;

import helpers.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPOM {

    public WebDriver driver;

    AbstractPOM(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitFor(int seconds) {
        SeleniumUtils.sleep(seconds);
    }
}
