package services;

import helpers.DataUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;

import java.util.Objects;

import static services.BNMService.parseElement;

public class Currency {

    public static Currency MDL = new Currency() {{
        setNominal("1");
        setValue("1");
        setCharCode("MDL");
    }};

    private String name;
    private String charCode;
    private double value;
    private int nominal;

    private Currency() {}

    public Currency(Logger log, Element element) {
        setName(parseElement(element, "Name"));
        setCharCode(parseElement(element, "CharCode"));
        setValue(parseElement(element, "Value"));
        setNominal(parseElement(element, "Nominal"));
        logIt(log);
    }

    /**
     * @param columnIndex 3, 6 or 7 - index of value column from https://www.curs.md/ru/curs_valutar/oficial
     */
    public Currency(Logger log, WebElement tableRow, int columnIndex) {
        setCharCode(tableRow.findElement(By.xpath("./td[1]/span")).getText());
        String name = tableRow.findElement(By.xpath("./td[2]/a")).getText();
        if (name.matches("\\d+.*")) {
            setNominal(name.split(" ")[0]);
            name = name.replaceAll("\\d", "");
        } else {
            setNominal("1");
        }
        setName(name);
        setValue(tableRow.findElement(By.xpath("./td[" + columnIndex + "]")).getText());
        logIt(log);
    }

    private void logIt(Logger log) {
        log.info("Currency [" + name + "] (" + charCode + ") with value <" + value + "> was created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode.trim();
    }

    public double getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = DataUtil.parseDouble(value);
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = Integer.parseInt(nominal.trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Double.compare(currency.value, value) == 0 &&
                nominal == currency.nominal &&
                Objects.equals(name, currency.name) &&
                Objects.equals(charCode, currency.charCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, charCode, value, nominal);
    }
}
