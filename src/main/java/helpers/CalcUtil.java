package helpers;

import org.openqa.selenium.WebElement;
import services.Currency;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CalcUtil {

    private static final String MDL = "MDL";
    private final Map<String, Currency> bnmCurrencies;
    private static final String MSG_FORMAT = "Incorrect conversion [%s] to [%s] (%s)";

    public CalcUtil(Map<String, Currency> bnmCurrencies) {
        this.bnmCurrencies = new HashMap<>(bnmCurrencies);
        if (!bnmCurrencies.containsKey(MDL))
            this.bnmCurrencies.put(MDL, Currency.MDL);
    }

    public void validateCurrency(String leftCode, WebElement leftInput, String rightCode, WebElement rightInput) {
        double left = DataUtil.parseDouble(leftInput);
        double actual = DataUtil.parseDouble(rightInput);
        assertEquals(String.format(MSG_FORMAT, leftCode, rightCode, left),
                fromMDL(toMDL(left, get(leftCode)), get(rightCode)), actual, DataUtil.EPSILON);
    }

    private Currency get(String charCode) {
        return bnmCurrencies.get(charCode);
    }

    private static double toMDL(double value, Currency from) {
        return value * from.getValue() / from.getNominal();
    }

    private static double fromMDL(double mdlValue, Currency to) {
        return mdlValue * to.getNominal() / to.getValue();
    }
}
