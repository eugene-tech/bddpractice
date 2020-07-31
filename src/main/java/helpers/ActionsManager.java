package helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsManager {
    private WebDriver driver;
    private static WebElement targetElement;
    private final Actions actions;

    public ActionsManager(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public Actions getActions() {
        return this.actions;
    }

    public ActionsManager controlA(WebElement element) {
        targetElement = element;
        actions.click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE).perform();
        return this;
    }

    public ActionsManager controlA() {
        actions.click(targetElement).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE).perform();
        return this;
    }

    public ActionsManager delete(WebElement element) {
        targetElement = element;
        actions.click(targetElement).sendKeys(Keys.DELETE);
        return this;
    }

    public ActionsManager sendKeys(CharSequence charSequence) {
        actions.sendKeys(charSequence);
        return this;
    }

    public ActionsManager delete() {
        actions.click(targetElement).sendKeys(Keys.DELETE);
        return this;
    }

    public void perform() {
        actions.perform();
    }


}
