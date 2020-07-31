package POMs.Pages;

import POMs.AbstractPOM;
import POMs.Page;

import helpers.SeleniumUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatesWidget extends AbstractPOM implements Page {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='head-menu']/a[2]")
    private WebElement headMenuGetRates;
    @FindBy(xpath = "//h1")
    private WebElement pageHeader;
    @FindBy(xpath = "//input[@id='width_v']")
    private WebElement widgetWidth;
    private final String iframeWidgetID = "Cursmd";

    public RatesWidget(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void changeWidgetWidth(String width) {
        log.info("Starting to change widget width on <" + width + ">");
        SeleniumUtils.getWaiter(driver).until(ExpectedConditions.visibilityOf(widgetWidth));
        SeleniumUtils.getInstance().getActionsManager().controlA(widgetWidth).delete().sendKeys(width).perform();
        pageHeader.click(); //unfocused input field
    }

    public void checkWidgetWidth(String expectedWidth, final String maxBound, final String minBound) {
        int expectedWidthInt = Integer.parseInt(expectedWidth);
        int maxBoundInt = Integer.parseInt(maxBound);
        int minBoundInt = Integer.parseInt(minBound);
        String currentWidthInInput= widgetWidth.getAttribute("value");
        log.info("Start to check width in <width input>. Current value -> "+currentWidthInInput);
        if (expectedWidthInt > maxBoundInt) {
            log.info("Actual -> "+currentWidthInInput);
            log.info("Expected -> "+maxBound);
            Assertions.assertThat(currentWidthInInput).isEqualTo(maxBound);
        } else if (expectedWidthInt < minBoundInt) {
            log.info("Actual -> "+currentWidthInInput);
            log.info("Expected -> "+minBound);
            Assertions.assertThat(currentWidthInInput).isEqualTo(minBound);
        } else {
            log.info("Actual -> "+currentWidthInInput);
            log.info("Expected -> "+expectedWidth);
            Assertions.assertThat(currentWidthInInput).isEqualTo(expectedWidth);
        }

        log.info("Switch to iframe widget width ID -> " + iframeWidgetID);
        driver.switchTo().frame(iframeWidgetID);

        String widgetWidthString = driver.findElement(By.xpath("//div[@id='cursmdcursBox']"))
                .getCssValue("width").replaceAll("px", "");
        log.info("Start to check Widget width. Current value -> "+widgetWidthString);
        log.info("Actual -> "+widgetWidthString);
        log.info("Expected -> "+expectedWidth);
        Assertions.assertThat(widgetWidthString).isEqualTo(expectedWidth);
        log.info("Returning to default Content");
        driver.switchTo().defaultContent();
    }

    @Override
    public void openThisPage() {
        log.info("Open this page -> <" + getMenuTitle() + ">");
        SeleniumUtils.getWaiter(driver).until(ExpectedConditions.elementToBeClickable(headMenuGetRates)).click();
    }

    @Override
    public void checkTitle(String expected) {
        Assertions.assertThat(getTitle()).isEqualTo(expected);
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getMenuTitle() {
        return headMenuGetRates.getText();
    }

    @Override
    public String getPageHeader() {
        return pageHeader.getText();
    }
}
