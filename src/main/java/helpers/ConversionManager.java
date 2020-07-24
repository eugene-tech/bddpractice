package helpers;

import dto.ValCurs;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import properties.LinksProperties;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Optional;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;

public class ConversionManager {

    public static ValCurs getValCursByDate(String dateWithFormat) throws JAXBException {
        return  (ValCurs) XMLParser.parseXML(
                HttpClient.get(LinksProperties.bnm_en_official_exchange_link+dateWithFormat), ValCurs.class);
    }
    public static String  parseDateFromWebElement(WebElement currentDate) throws ParseException {
        return  DateUtils.changeDateFormat(currentDate
                        .getAttribute("value"),
                "yyyy-MM-dd",
                "dd.MM.yyyy");
    }

    public static void checkConversionFromMDL(WebElement InputBlockForExchangeTO, ValCurs valCurs,
                                               String currentInputSum, WebElement code,
                                               Logger log){

        String currentOutputValue = InputBlockForExchangeTO.getAttribute("value").replaceAll(" ","");
        double currentInputSumDouble = DoubleUtils.getDoubleWithReplacement(currentInputSum);


        double exchangeRate = Optional.ofNullable(valCurs).get().getValute().stream().filter(val->val.getCharCode()
                .equals(code.getText()))
                .findFirst().get().getValue();


        String expectedResult = DoubleUtils.getDecimalWithFormat(currentInputSumDouble/exchangeRate,"#.##",
                RoundingMode.HALF_UP);

        log.info("Actual result: "+currentOutputValue);
        log.info("Expected result: "+expectedResult);
        Assertions.assertThat(currentOutputValue).isEqualTo(expectedResult);
    }

    public static void checkConversion(WebElement inputBlockForExchangeTO, ValCurs valCurs, String currentInputSum,
                                         String code1, WebElement code2, Logger log) {

        String currentOutputValue = inputBlockForExchangeTO.getAttribute("value").replaceAll(" ","");
        double currentInputSumDouble = DoubleUtils.getDoubleWithReplacement(currentInputSum);

        double exchangeInputRate = Optional.ofNullable(valCurs).get().getValute().stream().filter(val->val.getCharCode()
                .equals(code1))
                .findFirst().get().getValue();
        double exchangeOutRate = Optional.ofNullable(valCurs).get().getValute().stream().filter(val->val.getCharCode()
                .equals(code2.getText()))
                .findFirst().get().getValue();
        double ratio = exchangeInputRate/exchangeOutRate;


        String expectedResult = DoubleUtils.getDecimalWithFormat(currentInputSumDouble*ratio,"#.##",
                RoundingMode.HALF_UP);

        log.info("Actual result: "+currentOutputValue);
        log.info("Expected result: "+expectedResult);
        Assertions.assertThat(currentOutputValue).isEqualTo(expectedResult);
    }
    public static void checkConversionToMDL(WebElement inputBlockForExchangeTO, ValCurs valCurs,
                                             String currentInputSum,
                                         String code1, Logger log) {

        String currentOutputValue = inputBlockForExchangeTO.getAttribute("value").replaceAll(" ","");
        double currentInputSumDouble = DoubleUtils.getDoubleWithReplacement(currentInputSum);

        double exchangeInputRate = Optional.ofNullable(valCurs).get().getValute().stream().filter(val->val.getCharCode()
                .equals(code1))
                .findFirst().get().getValue();


        String expectedResult = DoubleUtils.getDecimalWithFormat(currentInputSumDouble*exchangeInputRate,"#.##",
                RoundingMode.HALF_UP);

        log.info("Actual result: "+currentOutputValue);
        log.info("Expected result: "+expectedResult);
        Assertions.assertThat(currentOutputValue).isEqualTo(expectedResult);
    }
}
